<?php
//error_reporting(E_ALL); ini_set('display_errors", 1);

 $con = mysqli_connect("localhost", "root", "Vegin2021!", "vegin");
 mysqli_query($con,'SET NAMES utf8');

 $email = $_POST["email"];
 $pw = $_POST["pw"];

 $statement = mysqli_prepare($con, "SELECT * FROM user WHERE email = ? AND pw = ?");
 mysqli_stmt_bind_param($statement, "ss", $email, $pw);
 mysqli_stmt_execute($statement);


 mysqli_stmt_store_result($statement);
 mysqli_stmt_bind_result($statement, $email, $pw, $name, $start_date, $type);

 $response = array();
 $response["success"] = false;

 while(mysqli_stmt_fetch($statement)) {
     $response["success"] = true;
     $response["email"] = $email;
     $response["pw"] = $pw;
     $response["name"] = $name;
     $response["start_date"] = $start_date;
     $response["type"] = $type;
 }

 echo json_encode($response);
?>

<!DOCTYPE html>
<head>
</head>
<body>
<div id = "login">
<form method = "post" action "login.php">
<p><input type = "text" name = "id" placeholder = "사용자이름" required></p>
<p><input type = "password" name = "pw" placeholder = "비밀번호" required></p>
<p><input type = "submit" value = "로그인"></p>
</form>
</div>
</body>
</html>
