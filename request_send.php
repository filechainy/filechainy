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
      padding-left: 20px;
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

    <br><br>          
    <h1>Request Sender</h1>
    
    <br>
    <form>
      <table width='80%'>
        <tr>
          <td>
            <label for="message">Links:</label>
          </td>
          <td>   
            <input type="text" id="message" name="message" value="apple,banana,cherry,orange,grape">
          </td>     
        </tr>
        <tr>
          <td>
           <label for="urls">Urls: &nbsp;</label>
          </td>
          <td>
        <textarea id="urls" name="urls" style="width: 100%;">
http://localhost/filechainy/receive.php?contents=
http://localhost/filechainy/receive_test.php?contents=
        </textarea>
         </td>
       </tr>
    </table>
      <button id="submit-button">Submit</button>
    </form>
    <script>
      const submitButton = document.querySelector('#submit-button');
      const messageField = document.querySelector('#message');
      const urlsField = document.querySelector('#urls');
  
      submitButton.addEventListener('click', () => {
        const links = messageField.value.split(',');
        const urls = urlsField.value.split('\n');
  
        links.forEach(link => {
          urls.forEach(url => {
            fetch(url + link)
              .then(response => response.json())
              .then(data => console.log(data))
              .catch(error => console.error(error));
          });
        });
      });
    </script>

      <i>2022 - filechainy</i>
    </div>
  </body>
</htlml>
