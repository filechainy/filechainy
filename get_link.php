<?php
// The URL to retrieve the hyperlinks from
$url = "";

// Retrieve the HTML content of the page
$html = file_get_contents($url);

// Create a DOM document
$dom = new DOMDocument();

// Load the HTML content into the DOM document
@$dom->loadHTML($html);

// Retrieve all the hyperlinks in the page
$links = $dom->getElementsByTagName('a');

// Loop through each hyperlink
foreach ($links as $link) {
    // Get the href attribute of the hyperlink
    $href = $link->getAttribute('href');
    
    // If the href doesn't start with 'http' or 'https'
    if (!preg_match("~^(?:f|ht)tps?://~i", $href)) {
        // Complete the href with the site address
        $href = $url . ltrim($href, '/');
    }
    
    // Print the href
    echo $href . "\n";
}
?>
