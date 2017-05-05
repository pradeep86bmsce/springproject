/**
 * 
 */
package com.org.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.org.models.Article;
import com.org.utils.UtilityFunctions;

/**
 * @author M1030876
 *
 */

@Controller
public class UploadController {

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String uploadHandler(MultipartHttpServletRequest request, RedirectAttributes redirectAttributes,
			Article article, Model model, @RequestParam("mode") String mode) {

		String errorMsg = null;
		String fullPath = null;
		String rootPath = "C:/";
		String fileName;
		StringTokenizer strTkn;
		String redirectUrl;
		File dir = new File(rootPath + File.separator + "upload");

		if (!dir.exists())
			dir.mkdirs();

		Iterator<String> itrator = request.getFileNames();

		while (itrator.hasNext()) {

			MultipartFile multifile = request.getFile(itrator.next());

			if (!multifile.getOriginalFilename().isEmpty()) {

				strTkn = new StringTokenizer(multifile.getOriginalFilename(), ".");

				fileName = (strTkn.nextToken()).concat(UtilityFunctions.randomString()).concat(".")
						.concat(strTkn.nextToken());

				try {
					byte[] bytes = multifile.getBytes();

					fullPath = dir.getAbsolutePath() + File.separator + fileName;
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fullPath)));

					stream.write(bytes);
					stream.flush();
					stream.close();

					String[] fileExt = { "txt", "doc", "docx", "pdf" };
					String[] imgExt = { "jpg", "png" };
					System.gc();
					if (FilenameUtils.isExtension(fileName, fileExt)) {
						new File(dir.getAbsolutePath() + File.separator + article.getContentName().trim()).delete();
						article.setContentName(fileName);
					} else if (FilenameUtils.isExtension(fileName, imgExt)) {
						new File(dir.getAbsolutePath() + File.separator + article.getImageName().trim()).delete();
						article.setImageName(fileName);
					} else {
						errorMsg = "Invalid file format";
					}
				} catch (Exception e) {

					errorMsg = "Failed to upload the files";
				}
			}
		}
		if (null != errorMsg) {
			model.addAttribute("errorMsg", errorMsg);
			return "article";
		} else {
			redirectAttributes.addFlashAttribute(article);
			redirectUrl = "/article/addArticle/".concat(mode);
			return "redirect:" + redirectUrl;
		}
	}

	@RequestMapping(value = "/download/{contentName:.+}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<byte[]> download(@PathVariable String contentName) {

		String rootPath = "C:/";

		try {

			File downloadFile = new File(rootPath + File.separator + "upload" + File.separator + contentName);

			if (downloadFile.exists()) {
				InputStream inputStream = new FileInputStream(downloadFile);

				byte[] out = org.apache.commons.io.IOUtils.toByteArray(inputStream);

				HttpHeaders responseHeaders = new HttpHeaders();

				String fileName = (downloadFile.getName().substring(0, downloadFile.getName().lastIndexOf(".") - 2)
						.concat(downloadFile.getName().substring(downloadFile.getName().lastIndexOf("."))));

				responseHeaders.add("content-disposition", "attachment; filename=" + fileName);

				responseHeaders.add("Content-Type", Files.probeContentType(downloadFile.toPath()));

				return new ResponseEntity<byte[]>(out, responseHeaders, HttpStatus.OK);
			} else {
				return new ResponseEntity<byte[]>("File Not Found".getBytes(StandardCharsets.UTF_8), HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<byte[]>("File Download Failed".getBytes(StandardCharsets.UTF_8), HttpStatus.OK);
		}
	}
}
