<?php
$con = mysqli_connect("localhost", "root", "Vegin2021!", "vegin");
mysqli_query($con, 'SET NAMES utf8');

$email = $_POST["email"];
$stmt = mysqli_prepare($con, "SELECT * FROM user WHERE email = '$email'");
mysqli_stmt_execute($stmt);
mysqli_stmt_store_result($stmt);
mysqli_stmt_bind_result($stmt, $email, $pw, $name, $start_date, $type);

$response = array();
while(mysqli_stmt_fetch($stmt)){
$response["success"] = true;
$response["email"] = $email;
$response["pw"] = $pw;
$response["name"] = $name;
$response["start_date"] = $start_date;
$response["type"] = $type;
}

echo json_encode($response, JSON_UNESCAPED_UNICODE);

?>
