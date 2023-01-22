<html>
  <head>
    <title>
      Filechainy
    </title>
    <link rel="icon" href="img/logo_1.png">
  </head>

  <style type="text/css"> 

    <?php

    $style = 1;

    // For use a random image in background 

    if ($style){

        $folder = 'simple';
        $limit = 2;

        $random_background = rand(0, $limit);
        $background_image = "background-image/$folder/$random_background.jpg";
        $present_color = "#FFFFFF";

    } else {
  
        $background_image = "background-image/$folder/$random_background.jpg";
        $present_color = "#333";
    }
  
    ?>

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
      background-image: url("<?php echo $background_image; ?>");

      background-size: 100%;
    }

    .present{
      letter-spacing: 4px;
      font-size: 54px;
      font-family: Verdana;
      color: <?php echo $present_color; ?>;
    }

    .present2{
      letter-spacing: 4px;
      font-size: 12px;
      font-family: Verdana;
      background-color: #fff;
      opacity: 0.66;
      padding: 4%;
    }

    .buttonBuy{      
      padding: 20px;
      background-color: #666;
      border: 2px solid #000:
      border-radius: 15px 15px 15px 15px;
      letter-spacing: 4px;
      font-size: 12px;
      font-family: Verdana;
      color: #FFFFFF;
      text-decoration: none;
      
    }

    .buttonBuy:hover{
      background-color: #999;
      color: #333; 
      border: 1px solid #AAA;

    }

    .bottom-right{
      position: absolute;
      bottom: 10px;
      right: 10px;
      background-color: #fff;
      opacity: 0.55;
      padding: 1%;
      border-radius: 5px 5px 5px 5px;    
    }

    a{
      color: #666666; 
      text-decoration: none;
    }

    a:hover{
      color: #777777; 
      text-decoration: none;

    }
    
  </style>

  <body>
      <table width='100%' class='top'>
        <tr>
          <td>
           &nbsp;
           <a href='index_pt.php'>PT</a> |  <a href='index.php'>ENG</a> &nbsp;&nbsp;
           <a href='doc_pt.html'>documentação</a> | <a href='about_pt.html'>sobre</a>
 
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <a href='upload.php' target='_blank'><img src='fatcow/add.png' alt='upload' height='20px'></a>&nbsp;
           <a href='filelist.php' target='_blank'><img src='fatcow/folder.png' alt='filest' height='20px'></a>&nbsp;
           <a href='groups.php' target='_blank'><img src='fatcow/mail_red.png' alt='groups' height='20px'></a>&nbsp;
           <a href='convert.php' target='_blank'><img src='fatcow/add_on.png' alt='convert' height='20px'></a>&nbsp;
           <a href='sourcecode.zip' target='_blank'><img src='fatcow/tux.png' alt='sourcecode' height='20px'></a>&nbsp;
           <a href='zip_hash.php' target='_blank'><img src='fatcow/box_closed.png' alt='hashes' height='20px'></a>&nbsp;
          </td>
          <td>
            <div align='right'>
              <a href='#'><img src='logos/pix_1.png' OnClick="alert('filechainy@gmail.com');" alt='pix' height='25px'></a>
              <a href='https://twitter.com/filechainy' target='_blank'><img src='logos/twitter_1.png' alt='twitter' height='25px'></a>
              <a href='http://t.me/filechainy' target='_blank'><img src='logos/telegram_1.png' alt='telegram' height='25px'></a>
              <a href='https://discord.gg/XqHb9veUvb' target='_blank'><img src='logos/discord_1.png' alt='discord' height='22px'></a>
              <a href='https://www.instagram.com/filechainy/' target='_blank'><img src='logos/instagram_1.png' alt='instagram' height='25px'></a>     
              <a href='https://www.reddit.com/user/filechainy' target='_blank'><img src='logos/reddit_1.png' alt='reddit' height='25px'></a>                    
              <a href='https://github.com/filechainy/filechainy' target='_blank'><img src='logos/github_1.png' alt='github' height='25px'></a>              
              <a href='https://wa.me/5591983608861' target='_blank'><img src='logos/whatsapp_1.png' alt='whatsapp' height='25px'></a>
                
            </div>
          </td>
        </tr>
      </table>

    <br><br><br><br>

    <div align='center'>
     
      <span class='present'>
        Ganhe transferindo arquivos
      </span><br><br><br>

      <table width='80%' style='text-justify: justify;' class='present2'>
        <tr>
          <td>
            <div style='text-align: justify;'>
              O Filechainy é um sistema de armazenamento descentralizado e com recompensas.
              O Filechainy transfere arquivos usando SHA1 e codificação base64.
              Cada arquivo é dividido em pequenos pedaços que recebem um nome SHA1.
              Esse design possibilita uma maneira simples e eficiente de transferir e checar a validade dos arquivos. <a href='about_pt.html'><u>Saber mais</u></a>
            </div>
          </td>
        </tr>
      </table>

      <br><br><br><br> 

      <a href='https://www.paypal.com/cgi-bin/webscr?cmd=_xclick&business=aerae4%40gmail%2ecom&lc=US&item_name=Registration%20fee&item_number=2&amount=15%2e00&currency_code=USD&button_subtype=services&no_note=0&bn=PP%2dBuyNowBF%3abtn_buynowCC_LG%2egif%3aNonHostedGuest' target='_blank' class='buttonBuy'>Pagar taxa de inscrição</a>
   
      <br><br><br><br><br><br>

      <i class='bottom-right'>2022 - filechainy</i>
    </div>
  </body>
</htlml>
