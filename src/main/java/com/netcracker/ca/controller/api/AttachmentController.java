package com.netcracker.ca.controller.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.netcracker.ca.model.Attachment;
import com.netcracker.ca.model.Team;
import com.netcracker.ca.model.dto.ExternalAttachmentDto;
import com.netcracker.ca.service.AttachmentService;
import com.netcracker.ca.validator.AttachmentFormValidator;

@RestController
public class AttachmentController extends BaseApiController {

	Logger logger = LogManager.getLogger("Error.Files");

	@Autowired
	private AttachmentService attService;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
    private AttachmentFormValidator attachmentFormValidator;
    
    @InitBinder("attachmentForm")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(attachmentFormValidator);
	}

	@PostMapping("admin/api/project/{projectId}/file")
	public Attachment uploadForProject(@RequestParam @Validated MultipartFile file, @RequestParam("text") String text,
			@PathVariable int projectId) throws IOException {
		try (InputStream input = file.getInputStream()) {
			Attachment att = new Attachment(text, file.getOriginalFilename(), file.getContentType());
			attService.addToProject(att, input,projectId);
			return att;
		}
	}
	
	@PostMapping("admin/api/project/{projectId}/file-ext")
	public Attachment addExternalForProject(@RequestBody ExternalAttachmentDto external, @PathVariable int projectId) throws IOException {
		Resource resource = resourceLoader.getResource(external.getLink());
		try (InputStream input = resource.getInputStream()) {
			Attachment att = new Attachment(external.getText(), external.getLink(), null);
			attService.addToProject(att, input, projectId);
			return att;
		}
	}

	@PostMapping("curator/api/file")
	public Attachment uploadForTeam(@RequestParam @Validated MultipartFile file, @RequestParam("text") String text,
			@SessionAttribute Team team) throws IOException {
		try (InputStream input = file.getInputStream()) {
			Attachment att = new Attachment(text, file.getOriginalFilename(), file.getContentType());
			attService.addToTeam(att, input, team.getId());
			return att;
		}
	}
	
	@PostMapping("curator/api/file-ext")
	public Attachment addExternalForTeam(@RequestBody ExternalAttachmentDto external, @SessionAttribute Team team) throws IOException {
		Resource resource = resourceLoader.getResource(external.getLink());
		try (InputStream input = resource.getInputStream()) {
			Attachment att = new Attachment(external.getText(), external.getLink(), null);
			attService.addToTeam(att, input, team.getId());
			return att;
		}
	}

	@GetMapping({ "admin/api/project/{projectId}/files" })
	public List<Attachment> getProjectAttachments(@PathVariable int projectId) {
		return attService.getProjectAttachments(projectId);
	}
	
	@GetMapping({ "curator/api/files-project", "student/api/files-project" })
	public List<Attachment> getProjectAttachments(@SessionAttribute Team team) {
		return attService.getProjectAttachments(team.getProject().getId());
	}

	@GetMapping({ "curator/api/files-team", "student/api/files-team" })
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

	@GetMapping({ "curator/api/file/{fileId}", "student/api/file/{fileId}" })
	public ResponseEntity<Resource> download(@PathVariable int fileId, @SessionAttribute Team team) throws IOException {
		if (!attService.belongsToTeam(fileId, team.getId()) && !attService.belongsToProject(fileId, team.getProject().getId()))
			return new ResponseEntity<Resource>(HttpStatus.FORBIDDEN);
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
		if(!attService.belongsToTeam(fileId, team.getId()))
			response.setStatus(403);
		attService.delete(fileId);
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(IOException.class)
	public void handleIOException(IOException ex) {
		logger.error("Failed to process file", ex);
	}
}