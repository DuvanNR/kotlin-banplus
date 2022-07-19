package com.example.banplus.utils

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

class NexgoPrinter: DeviceEngine {
    override fun getBeeper(): Beeper {
        TODO("Not yet implemented")
    }

    override fun getLEDDriver(): LEDDriver {
        TODO("Not yet implemented")
    }

    override fun getPrinter(): Printer {
        TODO("Not yet implemented")
    }

    override fun getSerialPortDriver(p0: Int): SerialPortDriver {
        TODO("Not yet implemented")
    }

    override fun getCardReader(): CardReader {
        TODO("Not yet implemented")
    }

    override fun getCPUCardHandler(p0: CardSlotTypeEnum?): CPUCardHandler {
        TODO("Not yet implemented")
    }

    override fun getM1CardHandler(): M1CardHandler {
        TODO("Not yet implemented")
    }

    override fun getMemoryCardHandler(p0: CardSlotTypeEnum?): MemoryCardHandler {
        TODO("Not yet implemented")
    }

    override fun getDesfireHandler(): DesfireHandler {
        TODO("Not yet implemented")
    }

    override fun getPinPad(): PinPad {
        TODO("Not yet implemented")
    }

    override fun getEmvHandler(p0: String?): EmvHandler {
        TODO("Not yet implemented")
    }

    override fun getScanner(): Scanner {
        TODO("Not yet implemented")
    }

    override fun getScanner2(): Scanner2 {
        TODO("Not yet implemented")
    }

    override fun getHSMDevice(): HSMDevice {
        TODO("Not yet implemented")
    }

    override fun setSystemClock(p0: String?) {
        TODO("Not yet implemented")
    }

    override fun installApp(p0: String?, p1: OnAppOperatListener?): Int {
        TODO("Not yet implemented")
    }

    override fun uninstallApp(p0: String?, p1: OnAppOperatListener?): Int {
        TODO("Not yet implemented")
    }

    override fun getDeviceInfo(): DeviceInfo {
        TODO("Not yet implemented")
    }

}