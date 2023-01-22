<?php error_reporting(0); ?>

<html>
  <head>
    <title>
      Filechainy
    </title>
    <link rel="icon" href="img/logo_1.png">
  </head>

  <style type="text/css"> 

  /* Add some styling to the messages */
  .message {
    margin: 10px 0;
    font-family: sans-serif;
    font-size: 16px;
  }
  .message b {
    font-weight: bold;
  }

  /* Add some styling to the form */
  form {
    margin: 20px 0;
    display: flex;
    flex-direction: column;
  }
  input[type="text"], textarea {
    font-family: sans-serif;
    font-size: 16px;
    padding: 5px;
    border: 1px solid #ccc;
  }
  input[type="submit"] {
    font-family: sans-serif;
    font-size: 16px;
    padding: 5px 10px;
    background-color: #4caf50;
    color: white;
    border: none;
    cursor: pointer;
  }

    .top{
      position: absolute;
      top: 0px;
      left: 0px;
      background-color: #444;
    }

    h1{
      color: #00FF55;
    }
  
    body{
      font-family: Arial;
      letter-spacing: 2px;
      color: #666666;
    }

    .present{
      letter-spacing: 4px;
      font-size: 54px;
      font-family: Verdana;
    }

    .present2{
      letter-spacing: 4px;
      font-size: 12px;
      font-family: Verdana;
    }

    .button{
      padding: 20px;
      background-color: #AAA;
      border: 2px solid #000:
      border-radius: 15px 15px 15px 15px;
      letter-spacing: 4px;
      font-size: 12px;
      font-family: Verdana;
      color: #FFFFFF;
      text-decoration: none;      
    }

    .button:hover{
      background-color: #666;
    }
   
    input{
    padding: 10px;
    }

  </style>

  <body>
    <table width='100%' class='top'>
      <tr>
        <td>
          <br>
        </td>
      </tr>
    </table>

    <br><br><br><br>

    <div align='center'>
    
      <span class='present'>
        Groups
      </span>

      <table width='80%' style='text-justify: justify;' class='present2'>
        <tr>
          <td>
            <div style='text-align: justify;'>                         
<?php

$group = "";
$name = "";

if (isset($_POST['group'])) {
    $group = $_POST['group'];
}

if (isset($_POST['name'])) {
    $name = $_POST['name'];
}

echo '<h1>' . $group . '</h1>';

if(!file_exists("groups")){
    mkdir("groups");
}

// Display the message form
echo "<form method='post' action='groups.php'>";
echo "<input type='text' name='group' placeholder='To group' value='$group'>";
echo "<input type='text' name='name' placeholder='Your name' value='$name'>";
echo "<textarea name='message' placeholder='Message'></textarea>";
echo "<input type='submit' name='submit' value='Submit'>";
echo "</form>";

// Check if the form has been submitted
if (isset($_POST['submit'])) {
  // Get the form data

  $message = $_POST['message']; 
   
  $filename_hash = sha1($group);

  $avoid_chars = array ('<', '>', "//");

  $name = str_replace($avoid_chars, "", $name); 
  $message = str_replace($avoid_chars, "", $message);

  $day = date("d");

  $month = date("m");

  $year = date("y");




  $date = $day . '/' . $month . '/' . $year; 

  $sha1_msg = sha1("$name $message $date");

  // Format the message as a string
  $messageString = "<a href='comment.php?comment_file=$sha1_msg'>[comment]</a> $date - $name: $message\n";

  // Append the message to the messages file
  file_put_contents("comments/$filename_hash", $messageString, FILE_APPEND);
}

// Read the messages file
$messages = file_get_contents("comments/$filename_hash");

// Split the messages into an array
$messages = explode("\n", $messages);

// Display the messages
foreach ($messages as $message) {
  echo "$message<br>";
}

?>
           </div>
          </td>
        </tr>
      </table>

      <br><br><br><br>
 
      <a href='index.php' class='button'>&nbsp; Back &nbsp;</a>

      <br><br><br><br>
      <i>2022 - filechainy</i>
    </div>
  </body>
</htlml>