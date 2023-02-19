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
        Paste
      </span>

      <table width='80%' style='text-justify: justify;' class='present2'>
        <tr>
          <td>
            <div style='text-align: justify;'>                         
<?php

if(!file_exists("paste")){

    mkdir("paste");

}

// Display the message form
echo "<form method='post' action=''>";
echo "<textarea name='message' placeholder='List of image URLs'></textarea>";
echo "<input type='submit' name='submit' value='Submit'>";
echo "</form>";

$filename_hash = "";

// Check if the form has been submitted
if (isset($_POST['submit'])) {
  // Get the form data
  $message = $_POST['message'];


$textarea_contents = $message;
$urls = explode("\n", $textarea_contents);

foreach ($urls as $url) {
    $ext = strtolower(pathinfo($url, PATHINFO_EXTENSION));
    if (!in_array($ext, array('jpg', 'jpeg', 'png', 'gif'))) {
        echo "$url is not a valid image URL\n"; die;
    } elseif (substr($url, 0, 4) !== 'http' && substr($url, 0, 5) !== 'https') {
        echo "$url is not a valid URL\n"; die;
    } 
}

  $filename_hash = sha1($message);

  // Format the message as a string
  $messageString = "$message";

  // Append the message to the messages file
  file_put_contents("paste/$filename_hash", $messageString, FILE_APPEND);

  echo "<a href='paste/$filename_hash'>View paste</a>";
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