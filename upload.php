<html>
  <head>
    <title>
      Filechainy
    </title>
    <link rel="icon" href="img/logo_1.png">
  </head>

  <style type="text/css"> 

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
        Upload
      </span>

      <table width='80%' style='text-justify: justify;' class='present2'>
        <tr>
          <td>
            <div style='text-align: justify;'>                         
<?php

// Check if the form was submitted
if (isset($_POST['submit'])) {
  // Check if any files were uploaded
  if (count($_FILES['files']['name']) > 0) {
    // Loop through each file
    for ($i = 0; $i < count($_FILES['files']['name']); $i++) {
      // Get the file extension
      $extension = pathinfo($_FILES['files']['name'][$i], PATHINFO_EXTENSION);
      $extension = strtolower($extension);
      // Check if the file extension is PHP
      if ($extension != 'php') {       
        if(!file_exists('files/' . $_FILES['files']['name'][$i])){
          // Move the uploaded file to the desired location
          move_uploaded_file($_FILES['files']['tmp_name'][$i], 'files/' . $_FILES['files']['name'][$i]);
          echo "File sent with success! ";
        } else {        
          echo 'File already exists! '; 
        }
      } else {
        // Display an error message if the file is a PHP file
        echo "PHP files are not allowed! ";
      }
    }
  }
}

?>
<br>
<!-- HTML form for file upload -->
<form action="" method="post" enctype="multipart/form-data">
  <label for="files"><h1>Select files to upload</h1></label>
  <input type="file" name="files[]" multiple>
  <input type="submit" name="submit" value="Upload">
</form>
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