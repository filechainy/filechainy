<?php

$file = fopen("files/files.txt", "r");
while (!feof($file)) {
  $url = trim(fgets($file));
  $ch = curl_init($url);
  $fp = fopen("files/".basename($url), "w");
  curl_setopt($ch, CURLOPT_FILE, $fp);
  curl_setopt($ch, CURLOPT_HEADER, 0);
  curl_exec($ch);
  curl_close($ch);
  fclose($fp);
}
fclose($file);

?>



