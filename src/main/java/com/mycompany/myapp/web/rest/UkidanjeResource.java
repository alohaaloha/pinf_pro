package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Ukidanje;
import com.mycompany.myapp.repository.UkidanjeRepository;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Ukidanje.
 */
@RestController
@RequestMapping("/api")
public class UkidanjeResource {

    private final Logger log = LoggerFactory.getLogger(UkidanjeResource.class);
        
    @Inject
    private UkidanjeRepository ukidanjeRepository;
    
    /**
     * POST  /ukidanjes : Create a new ukidanje.
     *
     * @param ukidanje the ukidanje to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ukidanje, or with status 400 (Bad Request) if the ukidanje has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/ukidanjes",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Ukidanje> createUkidanje(@Valid @RequestBody Ukidanje ukidanje) throws URISyntaxException {
        log.debug("REST request to save Ukidanje : {}", ukidanje);
        if (ukidanje.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("ukidanje", "idexists", "A new ukidanje cannot already have an ID")).body(null);
        }
        Ukidanje result = ukidanjeRepository.save(ukidanje);
        return ResponseEntity.created(new URI("/api/ukidanjes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("ukidanje", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ukidanjes : Updates an existing ukidanje.
     *
     * @param ukidanje the ukidanje to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ukidanje,
     * or with status 400 (Bad Request) if the ukidanje is not valid,
     * or with status 500 (Internal Server Error) if the ukidanje couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/ukidanjes",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Ukidanje> updateUkidanje(@Valid @RequestBody Ukidanje ukidanje) throws URISyntaxException {
        log.debug("REST request to update Ukidanje : {}", ukidanje);
        if (ukidanje.getId() == null) {
            return createUkidanje(ukidanje);
        }
        Ukidanje result = ukidanjeRepository.save(ukidanje);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("ukidanje", ukidanje.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ukidanjes : get all the ukidanjes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of ukidanjes in body
     */
    @RequestMapping(value = "/ukidanjes",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Ukidanje> getAllUkidanjes() {
        log.debug("REST request to get all Ukidanjes");
        List<Ukidanje> ukidanjes = ukidanjeRepository.findAll();
        return ukidanjes;
    }

    /**
     * GET  /ukidanjes/:id : get the "id" ukidanje.
     *
     * @param id the id of the ukidanje to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ukidanje, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/ukidanjes/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Ukidanje> getUkidanje(@PathVariable Long id) {
        log.debug("REST request to get Ukidanje : {}", id);
        Ukidanje ukidanje = ukidanjeRepository.findOne(id);
        return Optional.ofNullable(ukidanje)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /ukidanjes/:id : delete the "id" ukidanje.
     *
     * @param id the id of the ukidanje to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/ukidanjes/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteUkidanje(@PathVariable Long id) {
        log.debug("REST request to delete Ukidanje : {}", id);
        ukidanjeRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("ukidanje", id.toString())).build();
    }

}
