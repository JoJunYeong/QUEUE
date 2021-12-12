<head>
<meta http-equiv = "content-Type" content = "text/html" charset = "utf-8">
</head> 
<?php
$connect = mysql_connect("127.0.0.1", "root", "apmsetup");
mysql_selectdb("ticket"); 
mysql_query("set names UTF8");

$no=$_REQUEST['no'];
$t=$_REQUEST['t'];

$result=  mysql_result(mysql_query("select available from teacher where no='$no';"),0);



$xmlcode = "<?xml version = \"1.0\" encoding = \"UTF-8\" ?>\n"; 
$xmlcode .= "<result>$result</result>";


$dir = "C:/APM_Setup/htdocs/ticket"; 
$filename = $dir."/t_check_available.xml";

file_put_contents($filename, $xmlcode); 
?>

