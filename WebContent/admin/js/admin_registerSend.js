	
//	   var email = $("#memberEmail").val();
	var email = "yichin171@gmail.com"
	   console.log(email)
	   
	 $("#registerPass").click(registerPass());
     $("#registerfail").click(registerfail());

     function registerPass() {
         passEmail();
         ("#registerForm").submit(); 
     }
     function registerfail() {
         failEmail();
         ("#registerForm").submit(); 
     }



     function passEmail() {
         Email.send({
             Host: "smtp.gmail.com",
             Username: "massfood2021@gmail.com",
             Password: "TFA102TFA102",
             To: email,
             From: "美食埠<massfood2021@gmail.com>",
             Subject: "親愛的MassFood會員，資料已通過審核",
             Body: " <html><h4>歡迎使用MassFood系統</h4><p>親愛的會員:</p><p>您的資料已通過審核</p><p>快來使用本系統功能吧</p></html>"
         })
             .then(
                 message => console.log("審核信件已寄送!")
             );
     }



     function failEmail() {
         Email.send({
             Host: "smtp.gmail.com",
             Username: "massfood2021@gmail.com",
             Password: "TFA102TFA102",
             To: email,
             From: "美食埠<massfood2021@gmail.com>",
             Subject: "親愛的MassFood會員，資料未通過審核",
             Body: " <html><h4>歡迎使用MassFood系統</h4><p>親愛的會員:</p><p>您的資料填寫不全</p><p>請登入填寫後重新審核，謝謝。</p></html>"
         })
             .then(
                 message => console.log("審核信件已寄送!")
             );
     }
