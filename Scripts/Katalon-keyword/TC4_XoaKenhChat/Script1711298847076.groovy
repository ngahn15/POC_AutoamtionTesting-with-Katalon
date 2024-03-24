import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

nameChannel = WebUI.callTestCase(findTestCase('Katalon-keyword/TC3_SendMessage'), [:], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.click(findTestObject('MessagesPage/btnMenu'))

WebUI.click(findTestObject('MessagesPage/btnMenuItem', [('btnName') : 'Xóa kênh này']))

WebUI.waitForElementPresent(findTestObject('MessagesPage/DeleteDialog'), 5)

WebUI.takeElementScreenshotAsCheckpoint('DeleteDialog', findTestObject('MessagesPage/DeleteDialog'))

WebUI.waitForElementAttributeValue(findTestObject('MessagesPage/btn-confirm-delete-box', [('btnName') : 'Xác nhận xóa']), 'class', 'ready', 10)

WebUI.click(findTestObject('MessagesPage/btn-confirm-delete-box', [('btnName') : 'Xác nhận xóa']))

WebUI.click(findTestObject('MessagesPage/btn-confirm-delete-box', [('btnName') : 'Xác nhận xóa']))

WebUI.waitForElementClickable(findTestObject('HomePage/ListChatBox/btnTimKiem'), 5)

WebUI.click(findTestObject('HomePage/ListChatBox/btnTimKiem'))

WebUI.sendKeys(findTestObject('HomePage/TimKiemDialog/txtTimKiem'), nameChannel)

WebUI.click(findTestObject('HomePage/TimKiemDialog/search-tabs', [('tabName') : 'Kênh']))

WebUI.verifyElementNotPresent(findTestObject('HomePage/TimKiemDialog/result-item', [('nameChannel') : nameChannel]), 5)

