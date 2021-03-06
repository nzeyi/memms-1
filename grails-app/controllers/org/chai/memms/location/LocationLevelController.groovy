/**
 * Copyright (c) 2012, Clinton Health Access Initiative.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.chai.memms.location;

import org.chai.location.LocationLevel;
import org.chai.memms.AbstractEntityController;

class LocationLevelController extends AbstractEntityController {

	def locationService
	
	def bindParams(def entity) {
		entity.properties = params
	}

	def getModel(def entity) {
		[locationLevel: entity]
	}

	def getEntityClass(){
		return LocationLevel.class;
	}
	
	def getEntity(def id) {
		return LocationLevel.get(id);
	}

	def createEntity() {
		return new LocationLevel();
	}

	def deleteEntity(def entity) {
		if (entity.locations.size() != 0) {
			flash.message = message(code: 'location.level.haslocations', args: [message(code: getLabel(), default: 'entity'), params.id], default: 'Location level {0} still has associated locations.')
		}
		else {
			super.deleteEntity(entity)
		}
	}
	
	def getTemplate() {
		return '/entity/location/createLocationLevel'
	}

	def getLabel() {
		return 'location.level.label';
	}

	def list = {
		adaptParamsForList()
		List<LocationLevel> levels = LocationLevel.list(params);
		if(request.xhr)
			this.ajaxModel(levels)
		else{
		render (view: '/entity/list', model:[
			template:"location/locationLevelList",
			listTop:"location/locationLevelListTop",
			entities: levels,
			entityCount: LocationLevel.count(),
			code: getLabel(),
			names:names
		])
		}
	}
	
	def ajaxModel(def entities) {
		def model = [entities: entities,entityCount: entities.totalCount,names:names]
		def listHtml = g.render(template:"/entity/location/locationLevelList",model:model)
		render(contentType:"text/json") { results = [listHtml] }
	}
	
}
