package com.example.workorders.api;

import com.example.workorders.api.dto.CreateWorkOrderRequest;
import com.example.workorders.domain.WorkOrder;
import com.example.workorders.service.WorkOrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/work-orders")
public class WorkOrderController {
  private final WorkOrderService service;

  public WorkOrderController(WorkOrderService service) {
    this.service = service;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public WorkOrder create(@RequestBody @Valid CreateWorkOrderRequest req) throws Exception {
    return service.create(req);
  }
}