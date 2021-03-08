package com.os.orders.web.rest;

import com.os.orders.domain.PurchaseOrder;
import com.os.orders.service.PurchaseOrderService;
import com.os.orders.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.os.orders.domain.PurchaseOrder}.
 */
@RestController
@RequestMapping("/api")
public class PurchaseOrderResource {

    private final Logger log = LoggerFactory.getLogger(PurchaseOrderResource.class);

    private static final String ENTITY_NAME = "orderBackendPurchaseOrder";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderResource(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    /**
     * {@code POST  /purchase-orders} : Create a new purchaseOrder.
     *
     * @param purchaseOrder the purchaseOrder to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new purchaseOrder, or with status {@code 400 (Bad Request)} if the purchaseOrder has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/purchase-orders")
    public ResponseEntity<PurchaseOrder> createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) throws URISyntaxException {
        log.debug("REST request to save PurchaseOrder : {}", purchaseOrder);
        if (purchaseOrder.getPurchaseOrderId() != null) {
            throw new BadRequestAlertException("A new purchaseOrder cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PurchaseOrder result = purchaseOrderService.save(purchaseOrder);
        return ResponseEntity.created(new URI("/api/purchase-orders/" + result.getPurchaseOrderId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getPurchaseOrderId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /purchase-orders} : Updates an existing purchaseOrder.
     *
     * @param purchaseOrder the purchaseOrder to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated purchaseOrder,
     * or with status {@code 400 (Bad Request)} if the purchaseOrder is not valid,
     * or with status {@code 500 (Internal Server Error)} if the purchaseOrder couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/purchase-orders")
    public ResponseEntity<PurchaseOrder> updatePurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) throws URISyntaxException {
        log.debug("REST request to update PurchaseOrder : {}", purchaseOrder);
        if (purchaseOrder.getPurchaseOrderId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PurchaseOrder result = purchaseOrderService.save(purchaseOrder);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, purchaseOrder.getPurchaseOrderId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /purchase-orders} : get all the purchaseOrders.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of purchaseOrders in body.
     */
    @GetMapping("/purchase-orders")
    public List<PurchaseOrder> getAllPurchaseOrders() {
        log.debug("REST request to get all PurchaseOrders");
        return purchaseOrderService.findAll();
    }

    /**
     * {@code GET  /purchase-orders/:id} : get the "id" purchaseOrder.
     *
     * @param id the id of the purchaseOrder to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the purchaseOrder, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/purchase-orders/{id}")
    public ResponseEntity<PurchaseOrder> getPurchaseOrder(@PathVariable Long id) {
        log.debug("REST request to get PurchaseOrder : {}", id);
        Optional<PurchaseOrder> purchaseOrder = purchaseOrderService.findOne(id);
        return ResponseUtil.wrapOrNotFound(purchaseOrder);
    }

    /**
     * {@code DELETE  /purchase-orders/:id} : delete the "id" purchaseOrder.
     *
     * @param id the id of the purchaseOrder to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/purchase-orders/{id}")
    public ResponseEntity<Void> deletePurchaseOrder(@PathVariable Long id) {
        log.debug("REST request to delete PurchaseOrder : {}", id);
        purchaseOrderService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
