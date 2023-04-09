<?php
$con = mysqli_connect("localhost", "root", "Vegin2021!", "vegin");
mysqli_query($con,'SET NAMES utf8');

$email = $_POST["email"];
$text = $_POST["text"];
$date = $_POST["date"];
$num = $_POST["num"];

$response = array();
$response["success"] = false;

switch($num){
 case 1: //insert
 $statement = mysqli_prepare($con, "INSERT INTO calendar(text, email, date) VALUES('$text', '$email', '$date')"); 
 mysqli_stmt_execute($statement);
 break;

 case 2: //update
 $statement = mysqli_prepare($con, "UPDATE calendar SET text = '$text' WHERE email = '$email' and date = '$date'");
 mysqli_stmt_execute($statement); 
 break;

 case 3: //delete
 $statement = mysqli_prepare($con, "DELETE FROM calendar WHERE email = '$email' and date = '$date'"); 
 mysqli_stmt_execute($statement);
 break;
}

$response["success"] = true;


echo json_encode($response);
?>

