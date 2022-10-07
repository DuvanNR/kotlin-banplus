package com.example.banplus.component.template

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.banplus.R
import com.example.banplus._interface.iCommerce
import com.example.banplus._interface.iTransaction
import com.example.banplus.component.BtnNext
import com.example.banplus.context.LoadingContext
import com.example.banplus.context.LoginContext.startCardLogin
import com.example.banplus.context.TransactionContext
import com.example.banplus.db.schema.Commerce
import com.example.banplus.template.FormData
import com.example.banplus.utils.GoToActivity
import com.example.banplus.utils.addDecimals
import com.example.banplus.utils.getDatetime
import com.example.banplus.utils.iNameActivity
import com.example.banplus.viewmodel.VueltoViewModel

@Composable
fun FormBtns(
    numTab: Int,
    setNumTab: (Int) -> Unit,
    form: FormData,
    viewModel: VueltoViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val commerce = iCommerce.getCommerce(context)
    when (numTab) {
        0 -> {
            BtnNext(
                text = stringResource(id = R.string.siguiente),
                onClick = {
                    setNumTab(numTab + 1)
                },
                ico = painterResource(id = R.drawable.ic_next),
                enabled = form.numDocument.length >= 8 && form.typeDocument.key != "" && form.telefono.length == 11,
                modifier = Modifier
                    .padding(4.dp)
                    .height(49.dp)
                    .width(240.dp)
            )
        }
        1 -> {
            BtnNext(
                text = stringResource(id = R.string.siguiente),
                onClick = {
                    setNumTab(numTab + 1)
                },
                ico = painterResource(id = R.drawable.ic_next),
                enabled = form.monto != "0" && form.monto != "",
                modifier = Modifier
                    .padding(4.dp)
                    .height(49.dp)
                    .width(240.dp)
            )
        }
        2 -> {
            BtnNext(
                text = stringResource(id = R.string.Confirmar),
                onClick = {
                    val date = getDatetime()
                    startCardLogin {
                        viewModel.EmitPago(
                            iTransaction(
                                tipo = form.typeDocument.key,
                                cedula = form.numDocument,
                                telefono = form.telefono,
                                banco = form.banco.key,
                                monto = addDecimals(form.monto),
                                nameBanco = form.banco.title,
                                ref = "xxxx",
                                hora = date.hora,
                                fecha = date.fecha,
                                message = "",
                                state = false
                            ),
                            commerce = Commerce(
                                razonSocial = "${commerce?.razonSocial}",
                                rif = "${commerce?.rif}",
                                telefono = "${commerce?.telefono}",
                                tipo = "${commerce?.tipo}",
                                id = 1
                            ),
                            context
                        )
                    }
                },
                ico = painterResource(id = R.drawable.ic_next),
                enabled =
                form.numDocument.length >= 8 && form.typeDocument.key != "" && form.telefono.length == 11,
                modifier = Modifier
                    .padding(4.dp)
                    .height(49.dp)
                    .width(240.dp)
            )
        }
    }
    if (numTab in 1..2) {
        BtnNext(
            text = stringResource(id = R.string.volver),
            onClick = {
                setNumTab(numTab - 1)
            },
            ico = painterResource(id = R.drawable.ic_next),
            modifier = Modifier
                .padding(4.dp)
                .height(49.dp)
                .width(240.dp)
        )
    } else if (numTab == 0) {
        BtnNext(
            text = stringResource(id = R.string.home),
            onClick = {
                GoToActivity(iNameActivity.MAIN, context)
            },
            ico = painterResource(id = R.drawable.ic_next),
            modifier = Modifier
                .padding(4.dp)
                .height(49.dp)
                .width(240.dp)
        )
    }
}
