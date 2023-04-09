// 날짜 서치

<?php
$con = mysqli_connect("3.22.169.59", "root", "Vegin2021!", "vegin");
mysqli_query($con,'SET NAMES utf8');
$email = $_POST["email"];
$date = $_POST["date"];
$statement = mysqli_prepare($con, "SELECT * FROM calendar WHERE email = ? AND date = ?");
mysqli_stmt_bind_param($statement, "ss", $email, $date);
mysqli_stmt_execute($statement);

mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement, $no, $text, $email, $date);

while(mysqli_stmt_fetch($statement)){
$response["success"] = false;
 $response["no"] = $no;
 $response["text"] = $text;
 $response["email"] = $email;
 $response["date"] = $date;
}

$num = mysqli_stmt_num_rows($statement); // 반환되는 레코드셋의 총 레코드 개수 
//$query = "SELECT * from calendar where email = '$email' && date = '$date'";
//$result = mysqli_query($con, $query) or die (mysqli_error($con));

//$num = mysqli_num_rows($result);

$response = array();
$response["success"] = false;

if ($num == 0){
 $response["success"] = true;
 $response["no"] = null;
 $response["text"] = null;
 $response["email"] = null;
 $response["date"] = null;

 
}
else{
 $response["success"] = false;
 $response["no"] = $no;
 $response["text"] = $text;
 $response["email"] = $email;
 $response["date"] = $date;

}
echo json_encode($response);
echo $text;
?>
