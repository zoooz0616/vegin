<?php
$con = mysqli_connect("localhost", "root", "Vegin2021!", "vegin");
mysqli_query($con, 'SET NAMES utf8');

$email = $_POST["email"];
$type = $_POST['type'];

//email 찾아서내용 업데이트
$statement = mysqli_prepare($con, "UPDATE user set type = ? where email = '$email'");
mysqli_stmt_bind_param($statement, "s", $type); //값 연결
mysqli_stmt_execute($statement); //쿼리 실행

$response = array();
$response["success"] = true;
$response["email"] = $email;
$response["type"] = $type;


echo json_encode($response, JSON_UNESCAPED_UNICODE); //안드로이드로 전달



?>

