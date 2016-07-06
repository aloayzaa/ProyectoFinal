<?php

require_once '../dao/UPAODao.php';

// Parámetros
$ap_paterno = $_REQUEST["ap_paterno"];
$ap_materno= $_REQUEST["ap_materno"];
$nombres=trim($_REQUEST["nombres"]);
$dni=$_REQUEST["dni"];
$correo=$_REQUEST["correo"];

// Proceso
$dao = new UPAODao();
$rec = $dao->registrarCliente($ap_paterno,$ap_materno,$nombres,$dni,$correo);

if ($rec) {
    $rec["estado"] = 1; // Existe
} else {
    $rec["estado"] = 0; // No existe
}
$data = json_encode($rec);
// Retorno
echo $data;
?>