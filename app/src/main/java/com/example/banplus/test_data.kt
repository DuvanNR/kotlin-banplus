package com.example.banplus

import com.example.banplus._interface.iReportes
import com.example.banplus._interface.idropdown

val ResListA: List<iReportes> = listOf(
    iReportes(
        rif = "0000000111222",
        telefono = "04240000000",
        cedula = "V-12345678",
        fecha = "22/06/2022",
        hora = "8:30 a.m.",
        monto = "Bs. 10.000",
        status = "Aprobada"
    ),
    iReportes(
        rif = "0000000111222",
        telefono = "04240000000",
        cedula = "V-12345678",
        fecha = "22/06/2022",
        hora = "8:30 a.m.",
        monto = "Bs. 10.000",
        status = "Aprobada"
    ),
    iReportes(
        rif = "0000000111222",
        telefono = "04240000000",
        cedula = "V-12345678",
        fecha = "22/06/2022",
        hora = "8:30 a.m.",
        monto = "Bs. 10.000",
        status = "Aprobada"
    ),
    iReportes(
        rif = "0000000111222",
        telefono = "04240000000",
        cedula = "V-12345678",
        fecha = "22/06/2022",
        hora = "8:30 a.m.",
        monto = "Bs. 10.000",
        status = "Aprobada"
    ),
    iReportes(
        rif = "0000000111222",
        telefono = "04240000000",
        cedula = "V-12345678",
        fecha = "22/06/2022",
        hora = "8:30 a.m.",
        monto = "Bs. 10.000",
        status = "Aprobada"
    ),
    iReportes(
        rif = "0000000111222",
        telefono = "04240000000",
        cedula = "V-12345678",
        fecha = "22/06/2022",
        hora = "8:30 a.m.",
        monto = "Bs. 10.000",
        status = "Aprobada"
    ),
    iReportes(
        rif = "0000000111222",
        telefono = "04240000000",
        cedula = "V-12345678",
        fecha = "22/06/2022",
        hora = "8:30 a.m.",
        monto = "Bs. 10.000",
        status = "Aprobada"
    ),
    iReportes(
        rif = "0000000111222",
        telefono = "04240000000",
        cedula = "V-12345678",
        fecha = "22/06/2022",
        hora = "8:30 a.m.",
        monto = "Bs. 10.000",
        status = "Aprobada"
    ),
    iReportes(
        rif = "0000000111222",
        telefono = "04240000000",
        cedula = "V-12345678",
        fecha = "22/06/2022",
        hora = "8:30 a.m.",
        monto = "Bs. 10.000",
        status = "Aprobada"
    ),
    iReportes(
        rif = "0000000111222",
        telefono = "04240000000",
        cedula = "V-12345678",
        fecha = "22/06/2022",
        hora = "8:30 a.m.",
        monto = "Bs. 10.000",
        status = "Rechazado"
    ),
    )

val ListTypeDocument: List<idropdown> = listOf(
    idropdown("v", "V"),
    idropdown("p", "P"),
    idropdown("E", "E"),
)

val ListTypeRif: List<idropdown> = listOf(
    idropdown("G", "G"),
    idropdown("J", "J"),
    idropdown("v", "V"),
    idropdown("p", "P"),
    idropdown("E", "E"),
)


val BancosList: List<idropdown> = listOf(
    idropdown("0102", "Banco de Venezuela S.A."),
    idropdown("0104", "Venezolano de Crédito S.A."),
    idropdown("0105", "Banco Mercantil, C.A "),
    idropdown("0108", "Banco Provincial, S.A."),
    idropdown("0114", "Bancaribe C.A."),
    idropdown("0115", "Banco Exterior C.A."),
    idropdown("0128", "Banco Caroní C.A."),
    idropdown("0134", "Banesco S.A.C.A."),
    idropdown("0137", "Banco Sofitasa C.A."),
    idropdown("0138", "Banco Plaza C.A."),
    idropdown("0151", "BFC Banco Fondo Común C.A."),
    idropdown("0156", "100% Banco, C.A."),
    idropdown("0157", "DelSur, C.A."),
    idropdown("0163", "Banco del Tesoro, C.A."),
    idropdown("0166", "Banco Agrícola de Venezuela, C.A."),
    idropdown("0168", "Bancrecer, S.A."),
    idropdown("0169", "Mi Banco C.A."),
    idropdown("0171", "Banco Activo, C.A."),
    idropdown("0172", "Bancamiga, C.A."),
    idropdown("0174", "Banplus, C.A."),
    idropdown("0175", "Banco Bicentenario C.A."),
    idropdown("0177", "BANFANB"),
    idropdown("0191", "Banco Nacional de Crédito, C.A."),
)
val getLisNameShort: List<idropdown> = listOf(
    idropdown("0102", "Banco de Venezuela S.A"),
    idropdown("0104", "Venezolano de Crédito S.A"),
    idropdown("0108", "Banco Provincial S.A"),
    idropdown("0105", "Banco Mercantil C.A"),
    idropdown("0114", "Bancaribe C.A"),
    idropdown("0115", "Banco Exterior C.A"),
    idropdown("0128", "Banco Caroní C.A"),
    idropdown("0134", "Banesco S.A.C.A"),
    idropdown("0137", "Banco Sofitasa C.A"),
    idropdown("0138", "Banco Plaza C.A"),
    idropdown("0151", "BFC"),
    idropdown("0156", "100% Banco C.A"),
    idropdown("0157", "DelSur C.A"),
    idropdown("0163", "Banco del Tesoro C.A"),
    idropdown("0166", "BAV C.A"),
    idropdown("0168", "Bancrecer S.A"),
    idropdown("0169", "Mi Banco C.A"),
    idropdown("0171", "Banco Activo C.A"),
    idropdown("0172", "Bancamiga C.A"),
    idropdown("0174", "Banplus C.A"),
    idropdown("0175", "Banco Bicentenario C.A"),
    idropdown("0177", "BANFANB"),
    idropdown("0191", "BNC C.A"),
)


fun getBanconporCodigo(key: String):String {
    val name = getLisNameShort.find { it.key == key}
    return "${name?.title}"
}