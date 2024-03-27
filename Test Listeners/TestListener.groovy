import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.SimpleDateFormat

import org.apache.commons.io.FileUtils

import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.reporting.basic.reporting.ReportWriterUtil
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable

import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.logging.model.TestSuiteLogRecord

class TestListener {
	/**
	 * Executes before every test case starts.
	 * @param testCaseContext related information of the executed test case.
	 */
	@BeforeTestCase
	def sampleBeforeTestCase(TestCaseContext testCaseContext) {
		WebUI.openBrowser("")
		WebUI.maximizeWindow()
		WebUI.navigateToUrl(GlobalVariable.URL)
	}

	/**
	 * Executes after every test case ends.
	 * @param testCaseContext related information of the executed test case.
	 */
	@AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {
		WebUI.closeBrowser()
		
		def status = testCaseContext.getTestCaseStatus()
		GlobalVariable.TestTotal++
		if (status == 'FAILED') {
			GlobalVariable.TestFailed++
		}
		else if (status == 'PASSED') {
			GlobalVariable.TestPassed++
		}
		else if (status =='ERROR') {
			GlobalVariable.TestError++
		}else {
			GlobalVariable.TestUnknow++
		}
	}

	/**
	 * Executes before every test suite starts.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@BeforeTestSuite
	def sampleBeforeTestSuite(TestSuiteContext testSuiteContext) {
		println testSuiteContext.getTestSuiteId()
	}

	/**
	 * Executes after every test suite ends.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@AfterTestSuite
	def sampleAfterTestSuite(TestSuiteContext testSuiteContext) {
		System.out.println("Failed: " + GlobalVariable.TestFailed)
		System.out.println("Pass: " + GlobalVariable.TestPassed)
		System.out.println("Error: " + GlobalVariable.TestError)
		System.out.println("TestUnknow: " + GlobalVariable.TestUnknow)
		//		String outputCsv = "";
		//		String outputHtml = "";
		String outputPdf = "";

		try {
			FileUtils.copyDirectory(new File(RunConfiguration.getReportFolder()), new File(RunConfiguration.getReportFolder() + '_tmp'))
			File folderTmp = new File(RunConfiguration.getReportFolder() + '_tmp')
			String pathFolderTmp = folderTmp.getAbsolutePath()
			TestSuiteLogRecord suiteLogEntity = ReportWriterUtil.generate(pathFolderTmp);
			ReportWriterUtil.writePdfReport(suiteLogEntity, folderTmp);
			
			outputPdf = pathFolderTmp + "\\" + folderTmp.getName() + ".pdf"

		} catch (IOException e) {
			e.printStackTrace();
		}

		StringBuilder strMessage = new StringBuilder().append("Success: ").append(GlobalVariable.TestPassed).append("\n").append("Failed: ").append(GlobalVariable.TestFailed).append("\n").append("Error: ").append(GlobalVariable.TestError);
		SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		StringBuilder strMessageNew = new StringBuilder().append("Ngày ").append(simpleDate.format(new Date())).append("<b> chạy tool Katalon</b>").append(" test dự án:\n");
		strMessageNew.append("- Tổng số TestCase đã chạy là: ").append(GlobalVariable.TestTotal).append(" TestCase\n");
		strMessageNew.append("- Tổng số TestCase PASSED là: ").append(GlobalVariable.TestPassed).append(" TestCase\n");
		strMessageNew.append("- Tổng số TestCase FAILED là: ").append(GlobalVariable.TestFailed).append(" TestCase");


		// Send Result
		sendTelegramMessage(strMessageNew.toString(), "830548884", "bot5028236627:AAE0hNdvDFIy_kPM5kEaiCFLKYnGri3hNmg");

		//		if (outputCsv != "") {
		//			sendTelegramFile(outputCsv, "File ket qua test auto cho DWH","-986813068", "bot6209057074:AAHLfAavRiF2j14PUny2CG60_HAr-f2nt44")
		//		}
		//

		if (outputPdf != "") {
			sendTelegramFile(outputPdf, "File ket qua test","830548884", "bot5028236627:AAE0hNdvDFIy_kPM5kEaiCFLKYnGri3hNmg")
		}
	}
	
	def sendTelegramMessage(String message, String chatId, String botToken) {
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("curl", "-X", "POST", "https://api.telegram.org/" + botToken + "/sendMessage", "-d", "chat_id=" + chatId, "-d", "text=" + URLEncoder.encode(message), "-d", "parse_mode=HTML");

		try {

			Process process = processBuilder.start();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	def sendTelegramFile(String filePath, String caption, String chatId, String botToken) {
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command("curl", "-v","-F", "caption=" + caption, "-F", "document=@"+ filePath, "https://api.telegram.org/"+ botToken +"/sendDocument", "-F", "chat_id="+ chatId);
		try {

			Process process = processBuilder.start();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}