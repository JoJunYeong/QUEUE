<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> 
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup");
mysql_selectdb("ticket"); 
mysql_query("set names UTF8");


$name=$_REQUEST['name'];
$age=$_REQUEST['age'];
$seat=$_REQUEST['seat'];


$qry = "insert into student values('$name','$age','$seat',0,0,0,0,now());";
	$result = mysql_query($qry);
  

$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; 
$xmlcode .= "<result>$result</result>";


$dir = "C:/APM_Setup/htdocs/ticket"; 
$filename = $dir."/s_join.xml";

file_put_contents($filename, $xmlcode); 
?>

