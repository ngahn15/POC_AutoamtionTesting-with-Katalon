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

WebUI.callTestCase(findTestCase('Katalon-keyword/PageObjects/LoginPage/login(username, password)'), [:], FailureHandling.CONTINUE_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('HomePage/menuItem/btnMoreAction'), 5)

WebUI.click(findTestObject('HomePage/menuItem/btnMoreAction'))

WebUI.click(findTestObject('HomePage/menuItem/btnMoreAcitonItem', [('moreActionItem') : 'Tạo kênh mới']))

String nameChannel = 'abc'

WebUI.setText(findTestObject('ChatChannel/txtInForm', [('labelTxt') : 'Tên kênh']), nameChannel)

WebUI.scrollToElement(findTestObject('ChatChannel/btnInForm', [('btnName') : 'Lưu lại']), 5)

WebUI.click(findTestObject('ChatChannel/btnInForm', [('btnName') : 'Lưu lại']))

WebUI.verifyElementPresent(findTestObject('HomePage/ListChatBox/chatBoxItem', [('nameChannel') : nameChannel]), 5)

WebUI.takeElementScreenshotAsCheckpoint('listChatBox', findTestObject('HomePage/ListChatBox/menuChatBoxDialog'))

WebUI.closeBrowser()

return nameChannel

