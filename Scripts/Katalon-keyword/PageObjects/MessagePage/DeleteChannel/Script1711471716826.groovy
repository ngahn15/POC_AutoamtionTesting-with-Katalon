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

WebUI.callTestCase(findTestCase('Katalon-keyword/PageObjects/HomePage/Search Chatbox'), [('nameChannel') : nameChannel], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.callTestCase(findTestCase('Katalon-keyword/PageObjects/MessagePage/selectChatBoxResult'), [('nameChannel') : nameChannel],
	FailureHandling.CONTINUE_ON_FAILURE)

WebUI.waitForElementClickable(findTestObject('MessagesPage/btnMenu'), GlobalVariable.element_timeout)

WebUI.click(findTestObject('MessagesPage/btnMenu'))

WebUI.waitForElementClickable(findTestObject('MessagesPage/btnMenuItem', [('btnName') : 'Xóa kênh này']), GlobalVariable.element_timeout)

WebUI.click(findTestObject('MessagesPage/btnMenuItem', [('btnName') : 'Xóa kênh này']))

WebUI.waitForElementPresent(findTestObject('MessagesPage/DeleteDialog'), GlobalVariable.element_timeout)

WebUI.takeElementScreenshotAsCheckpoint('DeleteDialog', findTestObject('MessagesPage/DeleteDialog'))

WebUI.waitForElementClickable(findTestObject('MessagesPage/btn-confirm-delete-box', [('btnName') : 'Xác nhận xóa']), GlobalVariable.element_timeout)

WebUI.delay(GlobalVariable.element_timeout)

WebUI.click(findTestObject('MessagesPage/btn-confirm-delete-box', [('btnName') : 'Xác nhận xóa']))

