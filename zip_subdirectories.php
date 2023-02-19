<?php

// Set the path to the directory to be compressed
$dirPath = __DIR__ . '/categories';

// Create a new zip archive object
$zip = new ZipArchive();

// Set the name of the zip file to be created
$zipName = 'categories.zip';

// Open the zip file for writing
if ($zip->open($zipName, ZipArchive::CREATE | ZipArchive::OVERWRITE) === true) {
  
  // Create an iterator for the subdirectories of the directory to be compressed
  $iterator = new RecursiveDirectoryIterator($dirPath, RecursiveDirectoryIterator::SKIP_DOTS);
  
  // Create an iterator for the files in the subdirectories
  $files = new RecursiveIteratorIterator($iterator, RecursiveIteratorIterator::LEAVES_ONLY);
  
  // Loop through each file and add it to the zip archive
  foreach ($files as $name => $file) {
    // Get the real path of the file
    $filePath = $file->getRealPath();
    // Get the relative path of the file within the directory to be compressed
    $relativePath = substr($filePath, strlen($dirPath) + 1);
    // Add the file to the zip archive with the relative path as its name
    $zip->addFile($filePath, $relativePath);
  }
  
  // Close the zip archive
  $zip->close();
  
  // Output a message indicating that the zip file was created
  echo "Zip file created successfully.";
  
} else {
  
  // Output an error message if the zip file could not be created
  echo "Error creating zip file.";
  
}

echo " <a href='categories.zip'>download</a>";
?>