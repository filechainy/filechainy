<?php

$c = 0;

$folder= "background-image";

foreach(glob("$folder/simple/*") as $f){

    rename("$f", "$folder/simple/0$c.jpg");

$c++;
}

?>