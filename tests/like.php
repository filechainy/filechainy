<?php

function ip() {

    $ip = '';
    if (getenv('HTTP_CLIENT_IP'))
        $ip = getenv('HTTP_CLIENT_IP');
    else if(getenv('HTTP_X_FORWARDED_FOR'))
        $ip = getenv('HTTP_X_FORWARDED_FOR');
    else if(getenv('HTTP_X_FORWARDED'))
        $ip = getenv('HTTP_X_FORWARDED');
    else if(getenv('HTTP_FORWARDED_FOR'))
        $ip = getenv('HTTP_FORWARDED_FOR');
    else if(getenv('HTTP_FORWARDED'))
       $ip = getenv('HTTP_FORWARDED');
    else if(getenv('REMOTE_ADDR'))
        $ip = getenv('REMOTE_ADDR');
    else
        $ip = 'none';
    return $ip;
}

if($_GET['like_file'] != '' && file_exists($_GET['like_file'])){

    $z = 0;

    $like_file = $_GET['like_file'];

    $ip = ip();

    $ip_hash = sha1($ip);

    $dir_hash = 'likes/' . sha1($like_file);

    mkdir("$dir_hash");

    $write = fopen("$dir_hash/$ip_hash", "a");
    fwrite($write, "");
    fclose($write);

    foreach (glob("$dir_hash/*") as $u){
$z++;}

    $write = fopen("$dir_hash/total.txt", "w");
    fwrite($write, "$z");
    fclose($write);

}

?>
