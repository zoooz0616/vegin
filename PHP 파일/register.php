<?php
$con = mysqli_connect("localhost", "root", "Vegin2021!", "vegin");
mysqli_query($con, 'SET NAMES utf8');

$email = $_POST["email"];
//$pw = $_POST["pw"];
$name = $_POST['name'];
$start_date = $_POST['start_date'];
$type = $_POST['type'];

//email 찾아서내용 업데이트
$statement = mysqli_prepare($con, "UPDATE user set name = ?, start_date = ?, type = ? where email = '$email'");
mysqli_stmt_bind_param($statement, "sss", $name, $start_date, $type); //값 연결
mysqli_stmt_execute($statement); //쿼리 실행

$statement = mysqli_prepare($con, "SELECT start_date FROM user WHERE email = '$email'");
mysqli_stmt_execute($statement);
mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement, $start_date);

$response = array();
$response["success"] = true;
$response["email"] = $email;
$response["pw"] = $pw;
$response["name"] = $name;
$response["start_date"] = $start_date;
$response["type"] = $type;


echo json_encode($response); //안드로이드로 전달



?>
