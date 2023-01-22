<?php

// Set the URL of the website that you want to check
$url = 'https://google.com';

// Initialize a cURL session
$ch = curl_init();

// Set the URL and other options
curl_setopt($ch, CURLOPT_URL, $url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_FOLLOWLOCATION, true);
curl_setopt($ch, CURLOPT_HEADER, true);

// Execute the cURL request and get the response
$response = curl_exec($ch);

// Close the cURL session
curl_close($ch);

// Extract the response time from the response headers
$response_time = 0;
if (preg_match('/^Total-Time:\s*(\d+\.\d+)/mi', $response, $matches)) {
    $response_time = floatval($matches[1]);
}

// Output the response time
echo "Response time: $response_time seconds\n";

?>