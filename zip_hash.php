<?php

// Define the directory to be zipped
$dir = 'hash/';

// Get all files in the directory
$files = scandir($dir);

// Define the name of the zip file
$zipFile = 'archive.zip';

// Create a new zip archive object
$zip = new ZipArchive();

// Open the archive for writing
if ($zip->open($zipFile, ZipArchive::CREATE) !== true) {
    die("Failed to create archive\n");
}

// Iterate through the files in the directory
foreach ($files as $file) {
    // Skip current directory and parent directory
    if ($file === '.' || $file === '..') {
        continue;
    }

    // Add the file to the archive
    $zip->addFile($dir . '/' . $file);
}

// Close the archive
$zip->close();

echo "Hashes zipped";
echo " <a href='archive.zip'>download</a>";

?>