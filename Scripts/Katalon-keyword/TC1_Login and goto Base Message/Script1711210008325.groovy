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

WebUI.openBrowser('https://message.basestg.com')

WebUI.maximizeWindow()

WebUI.takeElementScreenshotAsCheckpoint('loginDialog', findTestObject('LoginPage/LoginDialog'))

WebUI.setText(findTestObject('LoginPage/txtEmail'), 'thoannv@realtel.vn')

WebUI.setText(findTestObject('LoginPage/txtPassword'), 'Thoan123456')

WebUI.click(findTestObject('LoginPage/btnLogin'))

if (WebUI.verifyElementNotVisible(findTestObject('HomePage/dialog_notification-request')) != true) {
	WebUI.takeElementScreenshot('HomePage/dialog_notification-request', findTestObject('HomePage/dialog_notification-request'))
	WebUI.click(findTestObject('HomePage/btnNotification-request_tiepTuc'))
}

if (WebUI.verifyElementVisible(findTestObject('HomePage/btnClose-noti-community'))) {
	WebUI.click(findTestObject('HomePage/btnClose-noti-community'))
}


WebUI.closeBrowser()

