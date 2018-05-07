/*
 * Copyright 2018 Alfresco, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.cloud.services.organization.rest.assembler;

import org.activiti.cloud.organization.core.model.Group;
import org.activiti.cloud.services.organization.rest.GroupController;
import org.activiti.cloud.services.organization.rest.resource.GroupResource;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Group resource assembler
 */
@Component
public class GroupResourceAssembler extends ResourceAssemblerSupport<Group, GroupResource> {

    public GroupResourceAssembler() {
        super(GroupController.class,
              GroupResource.class);
    }

    @Override
    public GroupResource toResource(Group group) {
        return new GroupResource(group,
                                 linkTo(methodOn(GroupController.class).getGroup(group.getId())).withSelfRel(),
                                 linkTo(methodOn(GroupController.class).getSubgroups(group.getId(),
                                                                                     Pageable.unpaged())).withRel("subgroups"));
    }
}
