package com.netcracker.ca.controller.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.netcracker.ca.model.Attachment;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.service.AttachmentFactory;
import com.netcracker.ca.service.AttachmentService;
import com.netcracker.ca.service.TeamService;
import com.netcracker.ca.utils.StorageException;

@RestController
public class AttachmentController extends BaseApiController {

	Logger logger = LogManager.getLogger("Error.Files");

	@Autowired
	private AttachmentService attService;

	@Autowired
	private AttachmentFactory attFactory;
	
	@Autowired
	private TeamService teamService;

	@PostMapping("admin/api/project/{projectId}/file")
	public Attachment uploadForProject(@RequestParam MultipartFile file, @RequestParam("text") String text,
			@PathVariable int projectId) throws IOException {
		try (InputStream input = file.getInputStream()) {
			Attachment att = attFactory.build(file.getOriginalFilename(), text, file.getContentType(), projectId, false);
			attService.addToProject(att, input, projectId);
			return att;
		}
	}
	
	@PostMapping("curator/api/file")
	public Attachment uploadForTeam(@RequestParam MultipartFile file, @RequestParam("text") String text,
			@SessionAttribute Team team) throws IOException {
		try (InputStream input = file.getInputStream()) {
			Attachment att = attFactory.build(file.getName(), text, file.getContentType(), team.getId(), true);
			attService.addToTeam(att, input, team.getId());
			return att;
		}
	}
	
	@GetMapping({"admin/api/project/{projectId}/files"})
	public List<Attachment> getProjectAttachments(@PathVariable int projectId) {
		return attService.getProjectAttachments(projectId);
	}
	
	@GetMapping({"curator/api/files", "student/api/files"})
	public List<Attachment> getTeamAttachments(@SessionAttribute Team team) {
		return attService.getTeamAttachments(team.getId());
	}

	@GetMapping("admin/api/file/{fileId}")
	public ResponseEntity<Resource> download(@PathVariable int fileId) throws IOException {
		Resource resource = attService.getAsResource(fileId);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Disposition", "attachment; filename=" + resource.getFilename().replace(" ", "_"));
		responseHeaders.set("Content-Length", String.valueOf(resource.contentLength()));
		return new ResponseEntity<Resource>(resource, responseHeaders, HttpStatus.OK);
	}
	
	@GetMapping({"curator/api/file/{fileId}", "student/api/file/{fileId}"})
	public ResponseEntity<Resource> download(@PathVariable int fileId, @SessionAttribute Team team) throws IOException {
		if(!team.equals(teamService.getForAttachment(fileId))) {
			return new ResponseEntity<Resource>(HttpStatus.FORBIDDEN);
		}
		Resource resource = attService.getAsResource(fileId);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Disposition", "attachment; filename=" + resource.getFilename().replace(" ", "_"));
		responseHeaders.set("Content-Length", String.valueOf(resource.contentLength()));
		return new ResponseEntity<Resource>(resource, responseHeaders, HttpStatus.OK);
	}

	@DeleteMapping("admin/api/file/{fileId}")
	public void delete(@PathVariable int fileId) {
		attService.delete(fileId);
	}
	
	@DeleteMapping("curator/api/file/{fileId}")
	public void delete(@PathVariable int fileId, @SessionAttribute Team team, HttpServletResponse response) {
		if(!team.equals(teamService.getForAttachment(fileId))) {
			response.setStatus(403);
		}
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(IOException.class)
	public void handleIOException(IOException ex) {
		logger.error("Failed to process file", ex);
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(StorageException.class)
	public void handleStorageException(StorageException ex) {
		logger.error("Storage operation failed", ex);
	}
}
