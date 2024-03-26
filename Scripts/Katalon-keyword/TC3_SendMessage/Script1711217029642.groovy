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

WebUI.callTestCase(findTestCase('Katalon-keyword/Page/LoginPage/Login'), [:], FailureHandling.CONTINUE_ON_FAILURE)

nameChannel = WebUI.callTestCase(findTestCase('Katalon-keyword/Common/TaoKenhChat'), [:], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.waitForElementClickable(findTestObject('HomePage/ListChatBox/btnTimKiem'), 5)

WebUI.click(findTestObject('HomePage/ListChatBox/btnTimKiem'))

WebUI.sendKeys(findTestObject('HomePage/TimKiemDialog/txtTimKiem'), nameChannel)

WebUI.click(findTestObject('HomePage/TimKiemDialog/search-tabs', [('tabName') : 'Kênh']))

WebUI.waitForElementClickable(findTestObject('HomePage/TimKiemDialog/result-item', [('nameChannel') : nameChannel]), 5)

WebUI.click(findTestObject('HomePage/TimKiemDialog/result-item', [('nameChannel') : nameChannel]))

WebUI.setText(findTestObject('MessagesPage/txtChatBox'), 'hello world')

WebUI.click(findTestObject('MessagesPage/btnSend'))

WebUI.verifyElementPresent(findTestObject('MessagesPage/channel-messages'), 5)

WebUI.takeElementScreenshotAsCheckpoint('chanelMessages', findTestObject('MessagesPage/channel-messages'))

return nameChannel
