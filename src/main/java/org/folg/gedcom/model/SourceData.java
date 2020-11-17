/*
 * Copyright 2011 Foundation for On-Line Genealogy, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.folg.gedcom.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SourceData extends NoteContainer {
   private List<DataEvent> dataEvents = null;
   private String agnc = null;

   public List<DataEvent> getDataEvents() {
      return dataEvents != null ? dataEvents : Collections.<DataEvent>emptyList();
   }

   public void setDataEvents(List<DataEvent> dataEvents) {
      this.dataEvents = dataEvents;
   }

   public void addDataEvent(DataEvent dataEvent) {
      if (dataEvents == null) {
         dataEvents = new ArrayList<DataEvent>();
      }
      dataEvents.add(dataEvent);
   }

   public String getAgency() {
      return agnc;
   }

   public void setAgency(String agnc) {
      this.agnc = agnc;
   }

   public void accept(Visitor visitor) {
      if (visitor.visit(this)) {
         for (DataEvent dataEvent : getDataEvents()) {
            dataEvent.accept(visitor);
         }
         super.visitContainedObjects(visitor);
         visitor.endVisit(this);
      }
   }
}
