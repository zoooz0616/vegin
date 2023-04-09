<?php
$con = mysqli_connect("localhost", "root", "Vegin2021!", "vegin");
mysqli_query($con,'SET NAMES utf8');

$email = $_POST["email"];

//$statement = mysqli_prepare($con, "SELECT * FROM map WHERE name LIKE '%$text%' || region LIKE '%$text%' || menu LIKE '%$text%'");

//$statement = mysqli_prepare($con, "SELECT * FROM map");
//mysqlir_stmt_execute($statement);
//mysqli_stmt_store_result($statement);
//mysqli_stmt_bind_result($statement, $no, $name,$type, $number, $region, $address, $latitude, $longtitude, $menu);

//$number = mysqli_stmt_num_rows($statement);
//echo $number;

//$response = array();

//while(mysqli_stmt_fetch($statement)){
//$response["success"] = true;
//$response["no"] = $no;
//$response["name"] = $name;
//$response["type"] = $type;
//$response["number"] = $number;
//$response["region"] = $region;
//$response["address"] = $address;
//$response["latitude"] = $latitude;
//$respaonse["longtitude"] = $longtitude;
//$response["menu"] = $menu;
//echo json_encode($response, JSON_UNESCAPED_UNICODE);
//}
//$row = array();
//while ($row = mysqli_stmt_fetch($statement)){
// echo json_encode($row);
//}


$map = array();
$response = array();
$response["success"] = true;
$response["map"] = array();

$query = "SELECT name, latitude, longtitude FROM map";
$result = mysqli_query($con, $query);

//while($map = mysqli_fetch_assoc($result)){
//print_r($row);
//$response["map"] = $map;
//echo json_encode($map, JSON_UNESCAPED_UNICODE);
//}

for($i = 0; $row = mysqli_fetch_array($result); $i++){
 $tempArr[name] = $row[name];
 $tempArr[latitude] = $row[latitude];
 $tempArr[longtitude] = $row[longtitude];
 array_push($response["map"], $tempArr);
}

//echo json_encode($map, JSON_UNESCAPED_UNICODE);
echo json_encode($response, JSON_UNESCAPED_UNICODE);

//echo json_encode($response, JSON_UNESCAPED_UNICODE);
?>
