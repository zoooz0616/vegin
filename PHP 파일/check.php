<?php
 $con = mysqli_connect("localhost", "root", "Vegin2021!", "vegin");
 mysqli_query($con, 'SET NAMES utf8');
 $email = $_POST["email"];
 $pw = $_POST["pw"];
 $name = 's';
 $start_date = '2021-01-01';
 $type = 's';


 $query = "select email from user where email = '$email'";
 $result = mysqli_query($con, $query)or die(mysqli_error($con));
 $count = mysqli_num_rows($result);
 $response = array();
 $response["success"] = false;

 if ($count == 0){ //만약 일치하는 이메일이 없다면
  $response["success"] = true;
  $statement = "INSERT INTO user values('".$email."', '".$pw."', '".$name."', '".$start_date."', '".$type."')";
  mysqli_query($con, $statement); //쿼리 실행


  $response["success"] = true;
  $response["email"] = $email;

//  echo json_encode($response); //안드로이드 스튜디오로 전달
}
 
 else{
  echo "<script>alert('ID 중복');</script>";
  $response["success"] = false;
 }

echo json_encode($response);

?>
