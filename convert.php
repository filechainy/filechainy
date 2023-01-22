<?php

/*/

Split the files of 'files' folder in various chunks
Each chunk is saved in the 'hash' folder and encoded in base64
The filename of each chunk is a sha1 hash based upon chunk contents
Is created a file (key) with the order that the chunks of a file must be read (to restore the file correctly)
/*/

// Size of each chunk (most browsers support a value between 256 and 64000)
$chunk_size = 8000;

$chunk = "";

$hash_path = "hash";

$files_path = "files";

// Directory containing the files to be converted and splitted
foreach (glob("$files_path/*") as $f){



    $hashes = "";

    // Get file contents
    $file = file_get_contents($f);

    // Convert file to base64
    $data = base64_encode($file);  

    // Get hash
    $file_hash = sha1($data);

    for ($x = 0; ; $x++){

        $chunk_start = $chunk_size * $x;

        $temp = substr($data, $chunk_start, $chunk_size);
                
        if($temp == ""){break;}

        // Get hash of each chunk
        $hash = sha1($temp); 

        if ($x == 0){

            $hashes = $hash;

        } else {

            $hashes = $hashes . "," . $hash;

        }

        // Write the chunks of file
        $file_base64 = fopen("$hash_path/$hash", "w");
        fwrite($file_base64, $temp);
        fclose($file_base64);       

    }
        // Create a file (key) with the order of the chunks of a file 
        $file_hash = fopen("keys/$file_hash", "w");
        fwrite($file_hash, $hashes);
        fclose($file_hash); 
}

echo "Files converted.";

?>

