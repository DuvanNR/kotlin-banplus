package com.example.banplus.utils

import android.app.Application
import com.nexgo.oaf.apiv3.APIProxy
import com.nexgo.oaf.apiv3.DeviceEngine
import com.nexgo.oaf.apiv3.DeviceInfo
import com.nexgo.oaf.apiv3.OnAppOperatListener
import com.nexgo.oaf.apiv3.card.cpu.CPUCardHandler
import com.nexgo.oaf.apiv3.card.memory.MemoryCardHandler
import com.nexgo.oaf.apiv3.card.mifare.DesfireHandler
import com.nexgo.oaf.apiv3.card.mifare.M1CardHandler
import com.nexgo.oaf.apiv3.device.beeper.Beeper
import com.nexgo.oaf.apiv3.device.led.LEDDriver
import com.nexgo.oaf.apiv3.device.pinpad.PinPad
import com.nexgo.oaf.apiv3.device.printer.Printer
import com.nexgo.oaf.apiv3.device.reader.CardReader
import com.nexgo.oaf.apiv3.device.reader.CardSlotTypeEnum
import com.nexgo.oaf.apiv3.device.scanner.Scanner
import com.nexgo.oaf.apiv3.device.scanner.Scanner2
import com.nexgo.oaf.apiv3.device.serialport.SerialPortDriver
import com.nexgo.oaf.apiv3.emv.EmvHandler
import com.nexgo.oaf.apiv3.hsm.HSMDevice

class NexgoApplication: Application() {
    var deviceEngine: DeviceEngine? = null
    override fun onCreate() {
        super.onCreate()
        deviceEngine = APIProxy.getDeviceEngine(this)
        deviceEngine?.getEmvHandler("app1")
    }
   }