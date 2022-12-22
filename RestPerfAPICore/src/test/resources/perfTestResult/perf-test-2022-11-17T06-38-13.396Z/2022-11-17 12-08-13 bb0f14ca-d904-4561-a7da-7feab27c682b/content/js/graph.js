/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
$(document).ready(function() {

    $(".click-title").mouseenter( function(    e){
        e.preventDefault();
        this.style.cursor="pointer";
    });
    $(".click-title").mousedown( function(event){
        event.preventDefault();
    });

    // Ugly code while this script is shared among several pages
    try{
        refreshHitsPerSecond(true);
    } catch(e){}
    try{
        refreshResponseTimeOverTime(true);
    } catch(e){}
    try{
        refreshResponseTimePercentiles();
    } catch(e){}
});


var responseTimePercentilesInfos = {
        data: {"result": {"minY": 6037.0, "minX": 0.0, "maxY": 31252.0, "series": [{"data": [[0.0, 6037.0], [0.1, 6037.0], [0.2, 6037.0], [0.3, 6037.0], [0.4, 6037.0], [0.5, 6037.0], [0.6, 6037.0], [0.7, 6037.0], [0.8, 6037.0], [0.9, 6037.0], [1.0, 6037.0], [1.1, 6037.0], [1.2, 6037.0], [1.3, 6037.0], [1.4, 6037.0], [1.5, 6037.0], [1.6, 6037.0], [1.7, 6037.0], [1.8, 6037.0], [1.9, 6037.0], [2.0, 6169.0], [2.1, 6169.0], [2.2, 6169.0], [2.3, 6169.0], [2.4, 6169.0], [2.5, 6169.0], [2.6, 6169.0], [2.7, 6169.0], [2.8, 6169.0], [2.9, 6169.0], [3.0, 6169.0], [3.1, 6169.0], [3.2, 6169.0], [3.3, 6169.0], [3.4, 6169.0], [3.5, 6169.0], [3.6, 6169.0], [3.7, 6169.0], [3.8, 6169.0], [3.9, 6169.0], [4.0, 6957.0], [4.1, 6957.0], [4.2, 6957.0], [4.3, 6957.0], [4.4, 6957.0], [4.5, 6957.0], [4.6, 6957.0], [4.7, 6957.0], [4.8, 6957.0], [4.9, 6957.0], [5.0, 6957.0], [5.1, 6957.0], [5.2, 6957.0], [5.3, 6957.0], [5.4, 6957.0], [5.5, 6957.0], [5.6, 6957.0], [5.7, 6957.0], [5.8, 6957.0], [5.9, 6957.0], [6.0, 7678.0], [6.1, 7678.0], [6.2, 7678.0], [6.3, 7678.0], [6.4, 7678.0], [6.5, 7678.0], [6.6, 7678.0], [6.7, 7678.0], [6.8, 7678.0], [6.9, 7678.0], [7.0, 7678.0], [7.1, 7678.0], [7.2, 7678.0], [7.3, 7678.0], [7.4, 7678.0], [7.5, 7678.0], [7.6, 7678.0], [7.7, 7678.0], [7.8, 7678.0], [7.9, 7678.0], [8.0, 15680.0], [8.1, 15680.0], [8.2, 15680.0], [8.3, 15680.0], [8.4, 15680.0], [8.5, 15680.0], [8.6, 15680.0], [8.7, 15680.0], [8.8, 15680.0], [8.9, 15680.0], [9.0, 15680.0], [9.1, 15680.0], [9.2, 15680.0], [9.3, 15680.0], [9.4, 15680.0], [9.5, 15680.0], [9.6, 15680.0], [9.7, 15680.0], [9.8, 15680.0], [9.9, 15680.0], [10.0, 18923.0], [10.1, 18923.0], [10.2, 18923.0], [10.3, 18923.0], [10.4, 18923.0], [10.5, 18923.0], [10.6, 18923.0], [10.7, 18923.0], [10.8, 18923.0], [10.9, 18923.0], [11.0, 18923.0], [11.1, 18923.0], [11.2, 18923.0], [11.3, 18923.0], [11.4, 18923.0], [11.5, 18923.0], [11.6, 18923.0], [11.7, 18923.0], [11.8, 18923.0], [11.9, 18923.0], [12.0, 18934.0], [12.1, 18934.0], [12.2, 18934.0], [12.3, 18934.0], [12.4, 18934.0], [12.5, 18934.0], [12.6, 18934.0], [12.7, 18934.0], [12.8, 18934.0], [12.9, 18934.0], [13.0, 18934.0], [13.1, 18934.0], [13.2, 18934.0], [13.3, 18934.0], [13.4, 18934.0], [13.5, 18934.0], [13.6, 18934.0], [13.7, 18934.0], [13.8, 18934.0], [13.9, 18934.0], [14.0, 20892.0], [14.1, 20892.0], [14.2, 20892.0], [14.3, 20892.0], [14.4, 20892.0], [14.5, 20892.0], [14.6, 20892.0], [14.7, 20892.0], [14.8, 20892.0], [14.9, 20892.0], [15.0, 20892.0], [15.1, 20892.0], [15.2, 20892.0], [15.3, 20892.0], [15.4, 20892.0], [15.5, 20892.0], [15.6, 20892.0], [15.7, 20892.0], [15.8, 20892.0], [15.9, 20892.0], [16.0, 21343.0], [16.1, 21343.0], [16.2, 21343.0], [16.3, 21343.0], [16.4, 21343.0], [16.5, 21343.0], [16.6, 21343.0], [16.7, 21343.0], [16.8, 21343.0], [16.9, 21343.0], [17.0, 21343.0], [17.1, 21343.0], [17.2, 21343.0], [17.3, 21343.0], [17.4, 21343.0], [17.5, 21343.0], [17.6, 21343.0], [17.7, 21343.0], [17.8, 21343.0], [17.9, 21343.0], [18.0, 22275.0], [18.1, 22275.0], [18.2, 22275.0], [18.3, 22275.0], [18.4, 22275.0], [18.5, 22275.0], [18.6, 22275.0], [18.7, 22275.0], [18.8, 22275.0], [18.9, 22275.0], [19.0, 22275.0], [19.1, 22275.0], [19.2, 22275.0], [19.3, 22275.0], [19.4, 22275.0], [19.5, 22275.0], [19.6, 22275.0], [19.7, 22275.0], [19.8, 22275.0], [19.9, 22275.0], [20.0, 22350.0], [20.1, 22350.0], [20.2, 22350.0], [20.3, 22350.0], [20.4, 22350.0], [20.5, 22350.0], [20.6, 22350.0], [20.7, 22350.0], [20.8, 22350.0], [20.9, 22350.0], [21.0, 22350.0], [21.1, 22350.0], [21.2, 22350.0], [21.3, 22350.0], [21.4, 22350.0], [21.5, 22350.0], [21.6, 22350.0], [21.7, 22350.0], [21.8, 22350.0], [21.9, 22350.0], [22.0, 23155.0], [22.1, 23155.0], [22.2, 23155.0], [22.3, 23155.0], [22.4, 23155.0], [22.5, 23155.0], [22.6, 23155.0], [22.7, 23155.0], [22.8, 23155.0], [22.9, 23155.0], [23.0, 23155.0], [23.1, 23155.0], [23.2, 23155.0], [23.3, 23155.0], [23.4, 23155.0], [23.5, 23155.0], [23.6, 23155.0], [23.7, 23155.0], [23.8, 23155.0], [23.9, 23155.0], [24.0, 23206.0], [24.1, 23206.0], [24.2, 23206.0], [24.3, 23206.0], [24.4, 23206.0], [24.5, 23206.0], [24.6, 23206.0], [24.7, 23206.0], [24.8, 23206.0], [24.9, 23206.0], [25.0, 23206.0], [25.1, 23206.0], [25.2, 23206.0], [25.3, 23206.0], [25.4, 23206.0], [25.5, 23206.0], [25.6, 23206.0], [25.7, 23206.0], [25.8, 23206.0], [25.9, 23206.0], [26.0, 23316.0], [26.1, 23316.0], [26.2, 23316.0], [26.3, 23316.0], [26.4, 23316.0], [26.5, 23316.0], [26.6, 23316.0], [26.7, 23316.0], [26.8, 23316.0], [26.9, 23316.0], [27.0, 23316.0], [27.1, 23316.0], [27.2, 23316.0], [27.3, 23316.0], [27.4, 23316.0], [27.5, 23316.0], [27.6, 23316.0], [27.7, 23316.0], [27.8, 23316.0], [27.9, 23316.0], [28.0, 23645.0], [28.1, 23645.0], [28.2, 23645.0], [28.3, 23645.0], [28.4, 23645.0], [28.5, 23645.0], [28.6, 23645.0], [28.7, 23645.0], [28.8, 23645.0], [28.9, 23645.0], [29.0, 23645.0], [29.1, 23645.0], [29.2, 23645.0], [29.3, 23645.0], [29.4, 23645.0], [29.5, 23645.0], [29.6, 23645.0], [29.7, 23645.0], [29.8, 23645.0], [29.9, 23645.0], [30.0, 23917.0], [30.1, 23917.0], [30.2, 23917.0], [30.3, 23917.0], [30.4, 23917.0], [30.5, 23917.0], [30.6, 23917.0], [30.7, 23917.0], [30.8, 23917.0], [30.9, 23917.0], [31.0, 23917.0], [31.1, 23917.0], [31.2, 23917.0], [31.3, 23917.0], [31.4, 23917.0], [31.5, 23917.0], [31.6, 23917.0], [31.7, 23917.0], [31.8, 23917.0], [31.9, 23917.0], [32.0, 23968.0], [32.1, 23968.0], [32.2, 23968.0], [32.3, 23968.0], [32.4, 23968.0], [32.5, 23968.0], [32.6, 23968.0], [32.7, 23968.0], [32.8, 23968.0], [32.9, 23968.0], [33.0, 23968.0], [33.1, 23968.0], [33.2, 23968.0], [33.3, 23968.0], [33.4, 23968.0], [33.5, 23968.0], [33.6, 23968.0], [33.7, 23968.0], [33.8, 23968.0], [33.9, 23968.0], [34.0, 24201.0], [34.1, 24201.0], [34.2, 24201.0], [34.3, 24201.0], [34.4, 24201.0], [34.5, 24201.0], [34.6, 24201.0], [34.7, 24201.0], [34.8, 24201.0], [34.9, 24201.0], [35.0, 24201.0], [35.1, 24201.0], [35.2, 24201.0], [35.3, 24201.0], [35.4, 24201.0], [35.5, 24201.0], [35.6, 24201.0], [35.7, 24201.0], [35.8, 24201.0], [35.9, 24201.0], [36.0, 24316.0], [36.1, 24316.0], [36.2, 24316.0], [36.3, 24316.0], [36.4, 24316.0], [36.5, 24316.0], [36.6, 24316.0], [36.7, 24316.0], [36.8, 24316.0], [36.9, 24316.0], [37.0, 24316.0], [37.1, 24316.0], [37.2, 24316.0], [37.3, 24316.0], [37.4, 24316.0], [37.5, 24316.0], [37.6, 24316.0], [37.7, 24316.0], [37.8, 24316.0], [37.9, 24316.0], [38.0, 24744.0], [38.1, 24744.0], [38.2, 24744.0], [38.3, 24744.0], [38.4, 24744.0], [38.5, 24744.0], [38.6, 24744.0], [38.7, 24744.0], [38.8, 24744.0], [38.9, 24744.0], [39.0, 24744.0], [39.1, 24744.0], [39.2, 24744.0], [39.3, 24744.0], [39.4, 24744.0], [39.5, 24744.0], [39.6, 24744.0], [39.7, 24744.0], [39.8, 24744.0], [39.9, 24744.0], [40.0, 25201.0], [40.1, 25201.0], [40.2, 25201.0], [40.3, 25201.0], [40.4, 25201.0], [40.5, 25201.0], [40.6, 25201.0], [40.7, 25201.0], [40.8, 25201.0], [40.9, 25201.0], [41.0, 25201.0], [41.1, 25201.0], [41.2, 25201.0], [41.3, 25201.0], [41.4, 25201.0], [41.5, 25201.0], [41.6, 25201.0], [41.7, 25201.0], [41.8, 25201.0], [41.9, 25201.0], [42.0, 25413.0], [42.1, 25413.0], [42.2, 25413.0], [42.3, 25413.0], [42.4, 25413.0], [42.5, 25413.0], [42.6, 25413.0], [42.7, 25413.0], [42.8, 25413.0], [42.9, 25413.0], [43.0, 25413.0], [43.1, 25413.0], [43.2, 25413.0], [43.3, 25413.0], [43.4, 25413.0], [43.5, 25413.0], [43.6, 25413.0], [43.7, 25413.0], [43.8, 25413.0], [43.9, 25413.0], [44.0, 25427.0], [44.1, 25427.0], [44.2, 25427.0], [44.3, 25427.0], [44.4, 25427.0], [44.5, 25427.0], [44.6, 25427.0], [44.7, 25427.0], [44.8, 25427.0], [44.9, 25427.0], [45.0, 25427.0], [45.1, 25427.0], [45.2, 25427.0], [45.3, 25427.0], [45.4, 25427.0], [45.5, 25427.0], [45.6, 25427.0], [45.7, 25427.0], [45.8, 25427.0], [45.9, 25427.0], [46.0, 25503.0], [46.1, 25503.0], [46.2, 25503.0], [46.3, 25503.0], [46.4, 25503.0], [46.5, 25503.0], [46.6, 25503.0], [46.7, 25503.0], [46.8, 25503.0], [46.9, 25503.0], [47.0, 25503.0], [47.1, 25503.0], [47.2, 25503.0], [47.3, 25503.0], [47.4, 25503.0], [47.5, 25503.0], [47.6, 25503.0], [47.7, 25503.0], [47.8, 25503.0], [47.9, 25503.0], [48.0, 25570.0], [48.1, 25570.0], [48.2, 25570.0], [48.3, 25570.0], [48.4, 25570.0], [48.5, 25570.0], [48.6, 25570.0], [48.7, 25570.0], [48.8, 25570.0], [48.9, 25570.0], [49.0, 25570.0], [49.1, 25570.0], [49.2, 25570.0], [49.3, 25570.0], [49.4, 25570.0], [49.5, 25570.0], [49.6, 25570.0], [49.7, 25570.0], [49.8, 25570.0], [49.9, 25570.0], [50.0, 25676.0], [50.1, 25676.0], [50.2, 25676.0], [50.3, 25676.0], [50.4, 25676.0], [50.5, 25676.0], [50.6, 25676.0], [50.7, 25676.0], [50.8, 25676.0], [50.9, 25676.0], [51.0, 25676.0], [51.1, 25676.0], [51.2, 25676.0], [51.3, 25676.0], [51.4, 25676.0], [51.5, 25676.0], [51.6, 25676.0], [51.7, 25676.0], [51.8, 25676.0], [51.9, 25676.0], [52.0, 25848.0], [52.1, 25848.0], [52.2, 25848.0], [52.3, 25848.0], [52.4, 25848.0], [52.5, 25848.0], [52.6, 25848.0], [52.7, 25848.0], [52.8, 25848.0], [52.9, 25848.0], [53.0, 25848.0], [53.1, 25848.0], [53.2, 25848.0], [53.3, 25848.0], [53.4, 25848.0], [53.5, 25848.0], [53.6, 25848.0], [53.7, 25848.0], [53.8, 25848.0], [53.9, 25848.0], [54.0, 26791.0], [54.1, 26791.0], [54.2, 26791.0], [54.3, 26791.0], [54.4, 26791.0], [54.5, 26791.0], [54.6, 26791.0], [54.7, 26791.0], [54.8, 26791.0], [54.9, 26791.0], [55.0, 26791.0], [55.1, 26791.0], [55.2, 26791.0], [55.3, 26791.0], [55.4, 26791.0], [55.5, 26791.0], [55.6, 26791.0], [55.7, 26791.0], [55.8, 26791.0], [55.9, 26791.0], [56.0, 27061.0], [56.1, 27061.0], [56.2, 27061.0], [56.3, 27061.0], [56.4, 27061.0], [56.5, 27061.0], [56.6, 27061.0], [56.7, 27061.0], [56.8, 27061.0], [56.9, 27061.0], [57.0, 27061.0], [57.1, 27061.0], [57.2, 27061.0], [57.3, 27061.0], [57.4, 27061.0], [57.5, 27061.0], [57.6, 27061.0], [57.7, 27061.0], [57.8, 27061.0], [57.9, 27061.0], [58.0, 27202.0], [58.1, 27202.0], [58.2, 27202.0], [58.3, 27202.0], [58.4, 27202.0], [58.5, 27202.0], [58.6, 27202.0], [58.7, 27202.0], [58.8, 27202.0], [58.9, 27202.0], [59.0, 27202.0], [59.1, 27202.0], [59.2, 27202.0], [59.3, 27202.0], [59.4, 27202.0], [59.5, 27202.0], [59.6, 27202.0], [59.7, 27202.0], [59.8, 27202.0], [59.9, 27202.0], [60.0, 27238.0], [60.1, 27238.0], [60.2, 27238.0], [60.3, 27238.0], [60.4, 27238.0], [60.5, 27238.0], [60.6, 27238.0], [60.7, 27238.0], [60.8, 27238.0], [60.9, 27238.0], [61.0, 27238.0], [61.1, 27238.0], [61.2, 27238.0], [61.3, 27238.0], [61.4, 27238.0], [61.5, 27238.0], [61.6, 27238.0], [61.7, 27238.0], [61.8, 27238.0], [61.9, 27238.0], [62.0, 27629.0], [62.1, 27629.0], [62.2, 27629.0], [62.3, 27629.0], [62.4, 27629.0], [62.5, 27629.0], [62.6, 27629.0], [62.7, 27629.0], [62.8, 27629.0], [62.9, 27629.0], [63.0, 27629.0], [63.1, 27629.0], [63.2, 27629.0], [63.3, 27629.0], [63.4, 27629.0], [63.5, 27629.0], [63.6, 27629.0], [63.7, 27629.0], [63.8, 27629.0], [63.9, 27629.0], [64.0, 27800.0], [64.1, 27800.0], [64.2, 27800.0], [64.3, 27800.0], [64.4, 27800.0], [64.5, 27800.0], [64.6, 27800.0], [64.7, 27800.0], [64.8, 27800.0], [64.9, 27800.0], [65.0, 27800.0], [65.1, 27800.0], [65.2, 27800.0], [65.3, 27800.0], [65.4, 27800.0], [65.5, 27800.0], [65.6, 27800.0], [65.7, 27800.0], [65.8, 27800.0], [65.9, 27800.0], [66.0, 27887.0], [66.1, 27887.0], [66.2, 27887.0], [66.3, 27887.0], [66.4, 27887.0], [66.5, 27887.0], [66.6, 27887.0], [66.7, 27887.0], [66.8, 27887.0], [66.9, 27887.0], [67.0, 27887.0], [67.1, 27887.0], [67.2, 27887.0], [67.3, 27887.0], [67.4, 27887.0], [67.5, 27887.0], [67.6, 27887.0], [67.7, 27887.0], [67.8, 27887.0], [67.9, 27887.0], [68.0, 27943.0], [68.1, 27943.0], [68.2, 27943.0], [68.3, 27943.0], [68.4, 27943.0], [68.5, 27943.0], [68.6, 27943.0], [68.7, 27943.0], [68.8, 27943.0], [68.9, 27943.0], [69.0, 27943.0], [69.1, 27943.0], [69.2, 27943.0], [69.3, 27943.0], [69.4, 27943.0], [69.5, 27943.0], [69.6, 27943.0], [69.7, 27943.0], [69.8, 27943.0], [69.9, 27943.0], [70.0, 28072.0], [70.1, 28072.0], [70.2, 28072.0], [70.3, 28072.0], [70.4, 28072.0], [70.5, 28072.0], [70.6, 28072.0], [70.7, 28072.0], [70.8, 28072.0], [70.9, 28072.0], [71.0, 28072.0], [71.1, 28072.0], [71.2, 28072.0], [71.3, 28072.0], [71.4, 28072.0], [71.5, 28072.0], [71.6, 28072.0], [71.7, 28072.0], [71.8, 28072.0], [71.9, 28072.0], [72.0, 28072.0], [72.1, 28072.0], [72.2, 28072.0], [72.3, 28072.0], [72.4, 28072.0], [72.5, 28072.0], [72.6, 28072.0], [72.7, 28072.0], [72.8, 28072.0], [72.9, 28072.0], [73.0, 28072.0], [73.1, 28072.0], [73.2, 28072.0], [73.3, 28072.0], [73.4, 28072.0], [73.5, 28072.0], [73.6, 28072.0], [73.7, 28072.0], [73.8, 28072.0], [73.9, 28072.0], [74.0, 28089.0], [74.1, 28089.0], [74.2, 28089.0], [74.3, 28089.0], [74.4, 28089.0], [74.5, 28089.0], [74.6, 28089.0], [74.7, 28089.0], [74.8, 28089.0], [74.9, 28089.0], [75.0, 28089.0], [75.1, 28089.0], [75.2, 28089.0], [75.3, 28089.0], [75.4, 28089.0], [75.5, 28089.0], [75.6, 28089.0], [75.7, 28089.0], [75.8, 28089.0], [75.9, 28089.0], [76.0, 28132.0], [76.1, 28132.0], [76.2, 28132.0], [76.3, 28132.0], [76.4, 28132.0], [76.5, 28132.0], [76.6, 28132.0], [76.7, 28132.0], [76.8, 28132.0], [76.9, 28132.0], [77.0, 28132.0], [77.1, 28132.0], [77.2, 28132.0], [77.3, 28132.0], [77.4, 28132.0], [77.5, 28132.0], [77.6, 28132.0], [77.7, 28132.0], [77.8, 28132.0], [77.9, 28132.0], [78.0, 28485.0], [78.1, 28485.0], [78.2, 28485.0], [78.3, 28485.0], [78.4, 28485.0], [78.5, 28485.0], [78.6, 28485.0], [78.7, 28485.0], [78.8, 28485.0], [78.9, 28485.0], [79.0, 28485.0], [79.1, 28485.0], [79.2, 28485.0], [79.3, 28485.0], [79.4, 28485.0], [79.5, 28485.0], [79.6, 28485.0], [79.7, 28485.0], [79.8, 28485.0], [79.9, 28485.0], [80.0, 28658.0], [80.1, 28658.0], [80.2, 28658.0], [80.3, 28658.0], [80.4, 28658.0], [80.5, 28658.0], [80.6, 28658.0], [80.7, 28658.0], [80.8, 28658.0], [80.9, 28658.0], [81.0, 28658.0], [81.1, 28658.0], [81.2, 28658.0], [81.3, 28658.0], [81.4, 28658.0], [81.5, 28658.0], [81.6, 28658.0], [81.7, 28658.0], [81.8, 28658.0], [81.9, 28658.0], [82.0, 28874.0], [82.1, 28874.0], [82.2, 28874.0], [82.3, 28874.0], [82.4, 28874.0], [82.5, 28874.0], [82.6, 28874.0], [82.7, 28874.0], [82.8, 28874.0], [82.9, 28874.0], [83.0, 28874.0], [83.1, 28874.0], [83.2, 28874.0], [83.3, 28874.0], [83.4, 28874.0], [83.5, 28874.0], [83.6, 28874.0], [83.7, 28874.0], [83.8, 28874.0], [83.9, 28874.0], [84.0, 28888.0], [84.1, 28888.0], [84.2, 28888.0], [84.3, 28888.0], [84.4, 28888.0], [84.5, 28888.0], [84.6, 28888.0], [84.7, 28888.0], [84.8, 28888.0], [84.9, 28888.0], [85.0, 28888.0], [85.1, 28888.0], [85.2, 28888.0], [85.3, 28888.0], [85.4, 28888.0], [85.5, 28888.0], [85.6, 28888.0], [85.7, 28888.0], [85.8, 28888.0], [85.9, 28888.0], [86.0, 29001.0], [86.1, 29001.0], [86.2, 29001.0], [86.3, 29001.0], [86.4, 29001.0], [86.5, 29001.0], [86.6, 29001.0], [86.7, 29001.0], [86.8, 29001.0], [86.9, 29001.0], [87.0, 29001.0], [87.1, 29001.0], [87.2, 29001.0], [87.3, 29001.0], [87.4, 29001.0], [87.5, 29001.0], [87.6, 29001.0], [87.7, 29001.0], [87.8, 29001.0], [87.9, 29001.0], [88.0, 29398.0], [88.1, 29398.0], [88.2, 29398.0], [88.3, 29398.0], [88.4, 29398.0], [88.5, 29398.0], [88.6, 29398.0], [88.7, 29398.0], [88.8, 29398.0], [88.9, 29398.0], [89.0, 29398.0], [89.1, 29398.0], [89.2, 29398.0], [89.3, 29398.0], [89.4, 29398.0], [89.5, 29398.0], [89.6, 29398.0], [89.7, 29398.0], [89.8, 29398.0], [89.9, 29398.0], [90.0, 29921.0], [90.1, 29921.0], [90.2, 29921.0], [90.3, 29921.0], [90.4, 29921.0], [90.5, 29921.0], [90.6, 29921.0], [90.7, 29921.0], [90.8, 29921.0], [90.9, 29921.0], [91.0, 29921.0], [91.1, 29921.0], [91.2, 29921.0], [91.3, 29921.0], [91.4, 29921.0], [91.5, 29921.0], [91.6, 29921.0], [91.7, 29921.0], [91.8, 29921.0], [91.9, 29921.0], [92.0, 30330.0], [92.1, 30330.0], [92.2, 30330.0], [92.3, 30330.0], [92.4, 30330.0], [92.5, 30330.0], [92.6, 30330.0], [92.7, 30330.0], [92.8, 30330.0], [92.9, 30330.0], [93.0, 30330.0], [93.1, 30330.0], [93.2, 30330.0], [93.3, 30330.0], [93.4, 30330.0], [93.5, 30330.0], [93.6, 30330.0], [93.7, 30330.0], [93.8, 30330.0], [93.9, 30330.0], [94.0, 30477.0], [94.1, 30477.0], [94.2, 30477.0], [94.3, 30477.0], [94.4, 30477.0], [94.5, 30477.0], [94.6, 30477.0], [94.7, 30477.0], [94.8, 30477.0], [94.9, 30477.0], [95.0, 30477.0], [95.1, 30477.0], [95.2, 30477.0], [95.3, 30477.0], [95.4, 30477.0], [95.5, 30477.0], [95.6, 30477.0], [95.7, 30477.0], [95.8, 30477.0], [95.9, 30477.0], [96.0, 31068.0], [96.1, 31068.0], [96.2, 31068.0], [96.3, 31068.0], [96.4, 31068.0], [96.5, 31068.0], [96.6, 31068.0], [96.7, 31068.0], [96.8, 31068.0], [96.9, 31068.0], [97.0, 31068.0], [97.1, 31068.0], [97.2, 31068.0], [97.3, 31068.0], [97.4, 31068.0], [97.5, 31068.0], [97.6, 31068.0], [97.7, 31068.0], [97.8, 31068.0], [97.9, 31068.0], [98.0, 31252.0], [98.1, 31252.0], [98.2, 31252.0], [98.3, 31252.0], [98.4, 31252.0], [98.5, 31252.0], [98.6, 31252.0], [98.7, 31252.0], [98.8, 31252.0], [98.9, 31252.0], [99.0, 31252.0], [99.1, 31252.0], [99.2, 31252.0], [99.3, 31252.0], [99.4, 31252.0], [99.5, 31252.0], [99.6, 31252.0], [99.7, 31252.0], [99.8, 31252.0], [99.9, 31252.0]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 100.0, "title": "Response Time Percentiles"}},
        getOptions: function() {
            return {
                series: {
                    points: { show: false }
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimePercentiles'
                },
                xaxis: {
                    tickDecimals: 1,
                    axisLabel: "Percentiles",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Percentile value in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : %x.2 percentile was %y ms"
                },
                selection: { mode: "xy" },
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimePercentiles"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimesPercentiles"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimesPercentiles"), dataset, prepareOverviewOptions(options));
        }
};

/**
 * @param elementId Id of element where we display message
 */
function setEmptyGraph(elementId) {
    $(function() {
        $(elementId).text("No graph series with filter="+seriesFilter);
    });
}

// Response times percentiles
function refreshResponseTimePercentiles() {
    var infos = responseTimePercentilesInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimePercentiles");
        return;
    }
    if (isGraph($("#flotResponseTimesPercentiles"))){
        infos.createGraph();
    } else {
        var choiceContainer = $("#choicesResponseTimePercentiles");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimesPercentiles", "#overviewResponseTimesPercentiles");
        $('#bodyResponseTimePercentiles .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var responseTimeDistributionInfos = {
        data: {"result": {"minY": 1.0, "minX": 6000.0, "maxY": 3.0, "series": [{"data": [[15600.0, 1.0], [18900.0, 2.0], [20800.0, 1.0], [21300.0, 1.0], [22200.0, 1.0], [22300.0, 1.0], [23100.0, 1.0], [23200.0, 1.0], [23300.0, 1.0], [23600.0, 1.0], [23900.0, 2.0], [24200.0, 1.0], [24300.0, 1.0], [24700.0, 1.0], [25200.0, 1.0], [25400.0, 2.0], [25500.0, 2.0], [25600.0, 1.0], [25800.0, 1.0], [26700.0, 1.0], [27000.0, 1.0], [27200.0, 2.0], [27600.0, 1.0], [27800.0, 2.0], [27900.0, 1.0], [28000.0, 3.0], [28100.0, 1.0], [28400.0, 1.0], [28600.0, 1.0], [28800.0, 2.0], [29000.0, 1.0], [29300.0, 1.0], [29900.0, 1.0], [30300.0, 1.0], [30400.0, 1.0], [31000.0, 1.0], [31200.0, 1.0], [6000.0, 1.0], [6100.0, 1.0], [6900.0, 1.0], [7600.0, 1.0]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 100, "maxX": 31200.0, "title": "Response Time Distribution"}},
        getOptions: function() {
            var granularity = this.data.result.granularity;
            return {
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimeDistribution'
                },
                xaxis:{
                    axisLabel: "Response times in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of responses",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                bars : {
                    show: true,
                    barWidth: this.data.result.granularity
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: function(label, xval, yval, flotItem){
                        return yval + " responses for " + label + " were between " + xval + " and " + (xval + granularity) + " ms";
                    }
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimeDistribution"), prepareData(data.result.series, $("#choicesResponseTimeDistribution")), options);
        }

};

// Response time distribution
function refreshResponseTimeDistribution() {
    var infos = responseTimeDistributionInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimeDistribution");
        return;
    }
    if (isGraph($("#flotResponseTimeDistribution"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimeDistribution");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        $('#footerResponseTimeDistribution .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var syntheticResponseTimeDistributionInfos = {
        data: {"result": {"minY": 50.0, "minX": 2.0, "ticks": [[0, "Requests having \nresponse time <= 500ms"], [1, "Requests having \nresponse time > 500ms and <= 1,500ms"], [2, "Requests having \nresponse time > 1,500ms"], [3, "Requests in error"]], "maxY": 50.0, "series": [{"data": [], "color": "#9ACD32", "isOverall": false, "label": "Requests having \nresponse time <= 500ms", "isController": false}, {"data": [], "color": "yellow", "isOverall": false, "label": "Requests having \nresponse time > 500ms and <= 1,500ms", "isController": false}, {"data": [[2.0, 50.0]], "color": "orange", "isOverall": false, "label": "Requests having \nresponse time > 1,500ms", "isController": false}, {"data": [], "color": "#FF6347", "isOverall": false, "label": "Requests in error", "isController": false}], "supportsControllersDiscrimination": false, "maxX": 2.0, "title": "Synthetic Response Times Distribution"}},
        getOptions: function() {
            return {
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendSyntheticResponseTimeDistribution'
                },
                xaxis:{
                    axisLabel: "Response times ranges",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                    tickLength:0,
                    min:-0.5,
                    max:3.5
                },
                yaxis: {
                    axisLabel: "Number of responses",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                bars : {
                    show: true,
                    align: "center",
                    barWidth: 0.25,
                    fill:.75
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: function(label, xval, yval, flotItem){
                        return yval + " " + label;
                    }
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var options = this.getOptions();
            prepareOptions(options, data);
            options.xaxis.ticks = data.result.ticks;
            $.plot($("#flotSyntheticResponseTimeDistribution"), prepareData(data.result.series, $("#choicesSyntheticResponseTimeDistribution")), options);
        }

};

// Response time distribution
function refreshSyntheticResponseTimeDistribution() {
    var infos = syntheticResponseTimeDistributionInfos;
    prepareSeries(infos.data, true);
    if (isGraph($("#flotSyntheticResponseTimeDistribution"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesSyntheticResponseTimeDistribution");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        $('#footerSyntheticResponseTimeDistribution .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var activeThreadsOverTimeInfos = {
        data: {"result": {"minY": 25.54000000000001, "minX": 1.66866708E12, "maxY": 25.54000000000001, "series": [{"data": [[1.66866708E12, 25.54000000000001]], "isOverall": false, "label": "Thread Group", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.66866708E12, "title": "Active Threads Over Time"}},
        getOptions: function() {
            return {
                series: {
                    stack: true,
                    lines: {
                        show: true,
                        fill: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of active threads",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 6,
                    show: true,
                    container: '#legendActiveThreadsOverTime'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                selection: {
                    mode: 'xy'
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : At %x there were %y active threads"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesActiveThreadsOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotActiveThreadsOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewActiveThreadsOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Active Threads Over Time
function refreshActiveThreadsOverTime(fixTimestamps) {
    var infos = activeThreadsOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 19800000);
    }
    if(isGraph($("#flotActiveThreadsOverTime"))) {
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesActiveThreadsOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotActiveThreadsOverTime", "#overviewActiveThreadsOverTime");
        $('#footerActiveThreadsOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var timeVsThreadsInfos = {
        data: {"result": {"minY": 6037.0, "minX": 1.0, "maxY": 31252.0, "series": [{"data": [[33.0, 24201.0], [32.0, 24316.0], [2.0, 31068.0], [35.0, 23917.0], [34.0, 23968.0], [37.0, 23316.0], [36.0, 23645.0], [39.0, 23155.0], [38.0, 23206.0], [41.0, 22275.0], [40.0, 22350.0], [43.0, 20892.0], [42.0, 21343.0], [45.0, 18928.5], [47.0, 7678.0], [46.0, 15680.0], [49.0, 6169.0], [48.0, 6957.0], [3.0, 30477.0], [50.0, 6037.0], [4.0, 30330.0], [5.0, 29921.0], [6.0, 29398.0], [7.0, 29001.0], [8.0, 28888.0], [9.0, 28874.0], [10.0, 28658.0], [11.0, 28485.0], [12.0, 28132.0], [13.0, 28089.0], [15.0, 28072.0], [16.0, 27943.0], [1.0, 31252.0], [17.0, 27887.0], [18.0, 27800.0], [19.0, 27629.0], [20.0, 27238.0], [21.0, 27202.0], [22.0, 27061.0], [23.0, 26791.0], [24.0, 25848.0], [25.0, 25676.0], [26.0, 25570.0], [27.0, 25503.0], [28.0, 25427.0], [29.0, 25413.0], [30.0, 25201.0], [31.0, 24744.0]], "isOverall": false, "label": "HTTP Request", "isController": false}, {"data": [[25.54000000000001, 24372.239999999998]], "isOverall": false, "label": "HTTP Request-Aggregated", "isController": false}], "supportsControllersDiscrimination": true, "maxX": 50.0, "title": "Time VS Threads"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    axisLabel: "Number of active threads",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response times in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: { noColumns: 2,show: true, container: '#legendTimeVsThreads' },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s: At %x.2 active threads, Average response time was %y.2 ms"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesTimeVsThreads"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotTimesVsThreads"), dataset, options);
            // setup overview
            $.plot($("#overviewTimesVsThreads"), dataset, prepareOverviewOptions(options));
        }
};

// Time vs threads
function refreshTimeVsThreads(){
    var infos = timeVsThreadsInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyTimeVsThreads");
        return;
    }
    if(isGraph($("#flotTimesVsThreads"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTimeVsThreads");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTimesVsThreads", "#overviewTimesVsThreads");
        $('#footerTimeVsThreads .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var bytesThroughputOverTimeInfos = {
        data : {"result": {"minY": 194.16666666666666, "minX": 1.66866708E12, "maxY": 5421299.333333333, "series": [{"data": [[1.66866708E12, 5421299.333333333]], "isOverall": false, "label": "Bytes received per second", "isController": false}, {"data": [[1.66866708E12, 194.16666666666666]], "isOverall": false, "label": "Bytes sent per second", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.66866708E12, "title": "Bytes Throughput Over Time"}},
        getOptions : function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity) ,
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Bytes / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendBytesThroughputOverTime'
                },
                selection: {
                    mode: "xy"
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y"
                }
            };
        },
        createGraph : function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesBytesThroughputOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotBytesThroughputOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewBytesThroughputOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Bytes throughput Over Time
function refreshBytesThroughputOverTime(fixTimestamps) {
    var infos = bytesThroughputOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 19800000);
    }
    if(isGraph($("#flotBytesThroughputOverTime"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesBytesThroughputOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotBytesThroughputOverTime", "#overviewBytesThroughputOverTime");
        $('#footerBytesThroughputOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var responseTimesOverTimeInfos = {
        data: {"result": {"minY": 24372.239999999998, "minX": 1.66866708E12, "maxY": 24372.239999999998, "series": [{"data": [[1.66866708E12, 24372.239999999998]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.66866708E12, "title": "Response Time Over Time"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average response time was %y ms"
                }
            };
        },
        createGraph: function() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Response Times Over Time
function refreshResponseTimeOverTime(fixTimestamps) {
    var infos = responseTimesOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyResponseTimeOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 19800000);
    }
    if(isGraph($("#flotResponseTimesOverTime"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimesOverTime", "#overviewResponseTimesOverTime");
        $('#footerResponseTimesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var latenciesOverTimeInfos = {
        data: {"result": {"minY": 1755.32, "minX": 1.66866708E12, "maxY": 1755.32, "series": [{"data": [[1.66866708E12, 1755.32]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.66866708E12, "title": "Latencies Over Time"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average response latencies in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendLatenciesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average latency was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesLatenciesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotLatenciesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewLatenciesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Latencies Over Time
function refreshLatenciesOverTime(fixTimestamps) {
    var infos = latenciesOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyLatenciesOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 19800000);
    }
    if(isGraph($("#flotLatenciesOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesLatenciesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotLatenciesOverTime", "#overviewLatenciesOverTime");
        $('#footerLatenciesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var connectTimeOverTimeInfos = {
        data: {"result": {"minY": 664.9000000000001, "minX": 1.66866708E12, "maxY": 664.9000000000001, "series": [{"data": [[1.66866708E12, 664.9000000000001]], "isOverall": false, "label": "HTTP Request", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.66866708E12, "title": "Connect Time Over Time"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getConnectTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Average Connect Time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendConnectTimeOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Average connect time was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesConnectTimeOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotConnectTimeOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewConnectTimeOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Connect Time Over Time
function refreshConnectTimeOverTime(fixTimestamps) {
    var infos = connectTimeOverTimeInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyConnectTimeOverTime");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 19800000);
    }
    if(isGraph($("#flotConnectTimeOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesConnectTimeOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotConnectTimeOverTime", "#overviewConnectTimeOverTime");
        $('#footerConnectTimeOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var responseTimePercentilesOverTimeInfos = {
        data: {"result": {"minY": 6037.0, "minX": 1.66866708E12, "maxY": 31252.0, "series": [{"data": [[1.66866708E12, 31252.0]], "isOverall": false, "label": "Max", "isController": false}, {"data": [[1.66866708E12, 29868.7]], "isOverall": false, "label": "90th percentile", "isController": false}, {"data": [[1.66866708E12, 31252.0]], "isOverall": false, "label": "99th percentile", "isController": false}, {"data": [[1.66866708E12, 30742.949999999997]], "isOverall": false, "label": "95th percentile", "isController": false}, {"data": [[1.66866708E12, 6037.0]], "isOverall": false, "label": "Min", "isController": false}, {"data": [[1.66866708E12, 25623.0]], "isOverall": false, "label": "Median", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.66866708E12, "title": "Response Time Percentiles Over Time (successful requests only)"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true,
                        fill: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Response Time in ms",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: '#legendResponseTimePercentilesOverTime'
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s : at %x Response time was %y ms"
                }
            };
        },
        createGraph: function () {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesResponseTimePercentilesOverTime"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotResponseTimePercentilesOverTime"), dataset, options);
            // setup overview
            $.plot($("#overviewResponseTimePercentilesOverTime"), dataset, prepareOverviewOptions(options));
        }
};

// Response Time Percentiles Over Time
function refreshResponseTimePercentilesOverTime(fixTimestamps) {
    var infos = responseTimePercentilesOverTimeInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 19800000);
    }
    if(isGraph($("#flotResponseTimePercentilesOverTime"))) {
        infos.createGraph();
    }else {
        var choiceContainer = $("#choicesResponseTimePercentilesOverTime");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimePercentilesOverTime", "#overviewResponseTimePercentilesOverTime");
        $('#footerResponseTimePercentilesOverTime .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var responseTimeVsRequestInfos = {
    data: {"result": {"minY": 18286.0, "minX": 1.0, "maxY": 27915.0, "series": [{"data": [[2.0, 22862.5], [1.0, 18286.0], [5.0, 26226.5], [10.0, 27915.0], [3.0, 24316.0], [7.0, 25503.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 10.0, "title": "Response Time Vs Request"}},
    getOptions: function() {
        return {
            series: {
                lines: {
                    show: false
                },
                points: {
                    show: true
                }
            },
            xaxis: {
                axisLabel: "Global number of requests per second",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            yaxis: {
                axisLabel: "Median Response Time in ms",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            legend: {
                noColumns: 2,
                show: true,
                container: '#legendResponseTimeVsRequest'
            },
            selection: {
                mode: 'xy'
            },
            grid: {
                hoverable: true // IMPORTANT! this is needed for tooltip to work
            },
            tooltip: true,
            tooltipOpts: {
                content: "%s : Median response time at %x req/s was %y ms"
            },
            colors: ["#9ACD32", "#FF6347"]
        };
    },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesResponseTimeVsRequest"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotResponseTimeVsRequest"), dataset, options);
        // setup overview
        $.plot($("#overviewResponseTimeVsRequest"), dataset, prepareOverviewOptions(options));

    }
};

// Response Time vs Request
function refreshResponseTimeVsRequest() {
    var infos = responseTimeVsRequestInfos;
    prepareSeries(infos.data);
    if (isGraph($("#flotResponseTimeVsRequest"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesResponseTimeVsRequest");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotResponseTimeVsRequest", "#overviewResponseTimeVsRequest");
        $('#footerResponseRimeVsRequest .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};


var latenciesVsRequestInfos = {
    data: {"result": {"minY": 1552.5, "minX": 1.0, "maxY": 1954.0, "series": [{"data": [[2.0, 1552.5], [1.0, 1654.0], [5.0, 1759.5], [10.0, 1758.0], [3.0, 1782.0], [7.0, 1954.0]], "isOverall": false, "label": "Successes", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 1000, "maxX": 10.0, "title": "Latencies Vs Request"}},
    getOptions: function() {
        return{
            series: {
                lines: {
                    show: false
                },
                points: {
                    show: true
                }
            },
            xaxis: {
                axisLabel: "Global number of requests per second",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            yaxis: {
                axisLabel: "Median Latency in ms",
                axisLabelUseCanvas: true,
                axisLabelFontSizePixels: 12,
                axisLabelFontFamily: 'Verdana, Arial',
                axisLabelPadding: 20,
            },
            legend: { noColumns: 2,show: true, container: '#legendLatencyVsRequest' },
            selection: {
                mode: 'xy'
            },
            grid: {
                hoverable: true // IMPORTANT! this is needed for tooltip to work
            },
            tooltip: true,
            tooltipOpts: {
                content: "%s : Median Latency time at %x req/s was %y ms"
            },
            colors: ["#9ACD32", "#FF6347"]
        };
    },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesLatencyVsRequest"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotLatenciesVsRequest"), dataset, options);
        // setup overview
        $.plot($("#overviewLatenciesVsRequest"), dataset, prepareOverviewOptions(options));
    }
};

// Latencies vs Request
function refreshLatenciesVsRequest() {
        var infos = latenciesVsRequestInfos;
        prepareSeries(infos.data);
        if(isGraph($("#flotLatenciesVsRequest"))){
            infos.createGraph();
        }else{
            var choiceContainer = $("#choicesLatencyVsRequest");
            createLegend(choiceContainer, infos);
            infos.createGraph();
            setGraphZoomable("#flotLatenciesVsRequest", "#overviewLatenciesVsRequest");
            $('#footerLatenciesVsRequest .legendColorBox > div').each(function(i){
                $(this).clone().prependTo(choiceContainer.find("li").eq(i));
            });
        }
};

var hitsPerSecondInfos = {
        data: {"result": {"minY": 0.8333333333333334, "minX": 1.66866708E12, "maxY": 0.8333333333333334, "series": [{"data": [[1.66866708E12, 0.8333333333333334]], "isOverall": false, "label": "hitsPerSecond", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.66866708E12, "title": "Hits Per Second"}},
        getOptions: function() {
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of hits / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendHitsPerSecond"
                },
                selection: {
                    mode : 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y.2 hits/sec"
                }
            };
        },
        createGraph: function createGraph() {
            var data = this.data;
            var dataset = prepareData(data.result.series, $("#choicesHitsPerSecond"));
            var options = this.getOptions();
            prepareOptions(options, data);
            $.plot($("#flotHitsPerSecond"), dataset, options);
            // setup overview
            $.plot($("#overviewHitsPerSecond"), dataset, prepareOverviewOptions(options));
        }
};

// Hits per second
function refreshHitsPerSecond(fixTimestamps) {
    var infos = hitsPerSecondInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 19800000);
    }
    if (isGraph($("#flotHitsPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesHitsPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotHitsPerSecond", "#overviewHitsPerSecond");
        $('#footerHitsPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
}

var codesPerSecondInfos = {
        data: {"result": {"minY": 0.8333333333333334, "minX": 1.66866708E12, "maxY": 0.8333333333333334, "series": [{"data": [[1.66866708E12, 0.8333333333333334]], "isOverall": false, "label": "200", "isController": false}], "supportsControllersDiscrimination": false, "granularity": 60000, "maxX": 1.66866708E12, "title": "Codes Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of responses / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendCodesPerSecond"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "Number of Response Codes %s at %x was %y.2 responses / sec"
                }
            };
        },
    createGraph: function() {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesCodesPerSecond"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotCodesPerSecond"), dataset, options);
        // setup overview
        $.plot($("#overviewCodesPerSecond"), dataset, prepareOverviewOptions(options));
    }
};

// Codes per second
function refreshCodesPerSecond(fixTimestamps) {
    var infos = codesPerSecondInfos;
    prepareSeries(infos.data);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 19800000);
    }
    if(isGraph($("#flotCodesPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesCodesPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotCodesPerSecond", "#overviewCodesPerSecond");
        $('#footerCodesPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var transactionsPerSecondInfos = {
        data: {"result": {"minY": 0.8333333333333334, "minX": 1.66866708E12, "maxY": 0.8333333333333334, "series": [{"data": [[1.66866708E12, 0.8333333333333334]], "isOverall": false, "label": "HTTP Request-success", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.66866708E12, "title": "Transactions Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of transactions / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendTransactionsPerSecond"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y transactions / sec"
                }
            };
        },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesTransactionsPerSecond"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotTransactionsPerSecond"), dataset, options);
        // setup overview
        $.plot($("#overviewTransactionsPerSecond"), dataset, prepareOverviewOptions(options));
    }
};

// Transactions per second
function refreshTransactionsPerSecond(fixTimestamps) {
    var infos = transactionsPerSecondInfos;
    prepareSeries(infos.data);
    if(infos.data.result.series.length == 0) {
        setEmptyGraph("#bodyTransactionsPerSecond");
        return;
    }
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 19800000);
    }
    if(isGraph($("#flotTransactionsPerSecond"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTransactionsPerSecond");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTransactionsPerSecond", "#overviewTransactionsPerSecond");
        $('#footerTransactionsPerSecond .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

var totalTPSInfos = {
        data: {"result": {"minY": 0.8333333333333334, "minX": 1.66866708E12, "maxY": 0.8333333333333334, "series": [{"data": [[1.66866708E12, 0.8333333333333334]], "isOverall": false, "label": "Transaction-success", "isController": false}, {"data": [], "isOverall": false, "label": "Transaction-failure", "isController": false}], "supportsControllersDiscrimination": true, "granularity": 60000, "maxX": 1.66866708E12, "title": "Total Transactions Per Second"}},
        getOptions: function(){
            return {
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    mode: "time",
                    timeformat: getTimeFormat(this.data.result.granularity),
                    axisLabel: getElapsedTimeLabel(this.data.result.granularity),
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20,
                },
                yaxis: {
                    axisLabel: "Number of transactions / sec",
                    axisLabelUseCanvas: true,
                    axisLabelFontSizePixels: 12,
                    axisLabelFontFamily: 'Verdana, Arial',
                    axisLabelPadding: 20
                },
                legend: {
                    noColumns: 2,
                    show: true,
                    container: "#legendTotalTPS"
                },
                selection: {
                    mode: 'xy'
                },
                grid: {
                    hoverable: true // IMPORTANT! this is needed for tooltip to
                                    // work
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s at %x was %y transactions / sec"
                },
                colors: ["#9ACD32", "#FF6347"]
            };
        },
    createGraph: function () {
        var data = this.data;
        var dataset = prepareData(data.result.series, $("#choicesTotalTPS"));
        var options = this.getOptions();
        prepareOptions(options, data);
        $.plot($("#flotTotalTPS"), dataset, options);
        // setup overview
        $.plot($("#overviewTotalTPS"), dataset, prepareOverviewOptions(options));
    }
};

// Total Transactions per second
function refreshTotalTPS(fixTimestamps) {
    var infos = totalTPSInfos;
    // We want to ignore seriesFilter
    prepareSeries(infos.data, false, true);
    if(fixTimestamps) {
        fixTimeStamps(infos.data.result.series, 19800000);
    }
    if(isGraph($("#flotTotalTPS"))){
        infos.createGraph();
    }else{
        var choiceContainer = $("#choicesTotalTPS");
        createLegend(choiceContainer, infos);
        infos.createGraph();
        setGraphZoomable("#flotTotalTPS", "#overviewTotalTPS");
        $('#footerTotalTPS .legendColorBox > div').each(function(i){
            $(this).clone().prependTo(choiceContainer.find("li").eq(i));
        });
    }
};

// Collapse the graph matching the specified DOM element depending the collapsed
// status
function collapse(elem, collapsed){
    if(collapsed){
        $(elem).parent().find(".fa-chevron-up").removeClass("fa-chevron-up").addClass("fa-chevron-down");
    } else {
        $(elem).parent().find(".fa-chevron-down").removeClass("fa-chevron-down").addClass("fa-chevron-up");
        if (elem.id == "bodyBytesThroughputOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshBytesThroughputOverTime(true);
            }
            document.location.href="#bytesThroughputOverTime";
        } else if (elem.id == "bodyLatenciesOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshLatenciesOverTime(true);
            }
            document.location.href="#latenciesOverTime";
        } else if (elem.id == "bodyCustomGraph") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshCustomGraph(true);
            }
            document.location.href="#responseCustomGraph";
        } else if (elem.id == "bodyConnectTimeOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshConnectTimeOverTime(true);
            }
            document.location.href="#connectTimeOverTime";
        } else if (elem.id == "bodyResponseTimePercentilesOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimePercentilesOverTime(true);
            }
            document.location.href="#responseTimePercentilesOverTime";
        } else if (elem.id == "bodyResponseTimeDistribution") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimeDistribution();
            }
            document.location.href="#responseTimeDistribution" ;
        } else if (elem.id == "bodySyntheticResponseTimeDistribution") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshSyntheticResponseTimeDistribution();
            }
            document.location.href="#syntheticResponseTimeDistribution" ;
        } else if (elem.id == "bodyActiveThreadsOverTime") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshActiveThreadsOverTime(true);
            }
            document.location.href="#activeThreadsOverTime";
        } else if (elem.id == "bodyTimeVsThreads") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTimeVsThreads();
            }
            document.location.href="#timeVsThreads" ;
        } else if (elem.id == "bodyCodesPerSecond") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshCodesPerSecond(true);
            }
            document.location.href="#codesPerSecond";
        } else if (elem.id == "bodyTransactionsPerSecond") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTransactionsPerSecond(true);
            }
            document.location.href="#transactionsPerSecond";
        } else if (elem.id == "bodyTotalTPS") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshTotalTPS(true);
            }
            document.location.href="#totalTPS";
        } else if (elem.id == "bodyResponseTimeVsRequest") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshResponseTimeVsRequest();
            }
            document.location.href="#responseTimeVsRequest";
        } else if (elem.id == "bodyLatenciesVsRequest") {
            if (isGraph($(elem).find('.flot-chart-content')) == false) {
                refreshLatenciesVsRequest();
            }
            document.location.href="#latencyVsRequest";
        }
    }
}

/*
 * Activates or deactivates all series of the specified graph (represented by id parameter)
 * depending on checked argument.
 */
function toggleAll(id, checked){
    var placeholder = document.getElementById(id);

    var cases = $(placeholder).find(':checkbox');
    cases.prop('checked', checked);
    $(cases).parent().children().children().toggleClass("legend-disabled", !checked);

    var choiceContainer;
    if ( id == "choicesBytesThroughputOverTime"){
        choiceContainer = $("#choicesBytesThroughputOverTime");
        refreshBytesThroughputOverTime(false);
    } else if(id == "choicesResponseTimesOverTime"){
        choiceContainer = $("#choicesResponseTimesOverTime");
        refreshResponseTimeOverTime(false);
    }else if(id == "choicesResponseCustomGraph"){
        choiceContainer = $("#choicesResponseCustomGraph");
        refreshCustomGraph(false);
    } else if ( id == "choicesLatenciesOverTime"){
        choiceContainer = $("#choicesLatenciesOverTime");
        refreshLatenciesOverTime(false);
    } else if ( id == "choicesConnectTimeOverTime"){
        choiceContainer = $("#choicesConnectTimeOverTime");
        refreshConnectTimeOverTime(false);
    } else if ( id == "choicesResponseTimePercentilesOverTime"){
        choiceContainer = $("#choicesResponseTimePercentilesOverTime");
        refreshResponseTimePercentilesOverTime(false);
    } else if ( id == "choicesResponseTimePercentiles"){
        choiceContainer = $("#choicesResponseTimePercentiles");
        refreshResponseTimePercentiles();
    } else if(id == "choicesActiveThreadsOverTime"){
        choiceContainer = $("#choicesActiveThreadsOverTime");
        refreshActiveThreadsOverTime(false);
    } else if ( id == "choicesTimeVsThreads"){
        choiceContainer = $("#choicesTimeVsThreads");
        refreshTimeVsThreads();
    } else if ( id == "choicesSyntheticResponseTimeDistribution"){
        choiceContainer = $("#choicesSyntheticResponseTimeDistribution");
        refreshSyntheticResponseTimeDistribution();
    } else if ( id == "choicesResponseTimeDistribution"){
        choiceContainer = $("#choicesResponseTimeDistribution");
        refreshResponseTimeDistribution();
    } else if ( id == "choicesHitsPerSecond"){
        choiceContainer = $("#choicesHitsPerSecond");
        refreshHitsPerSecond(false);
    } else if(id == "choicesCodesPerSecond"){
        choiceContainer = $("#choicesCodesPerSecond");
        refreshCodesPerSecond(false);
    } else if ( id == "choicesTransactionsPerSecond"){
        choiceContainer = $("#choicesTransactionsPerSecond");
        refreshTransactionsPerSecond(false);
    } else if ( id == "choicesTotalTPS"){
        choiceContainer = $("#choicesTotalTPS");
        refreshTotalTPS(false);
    } else if ( id == "choicesResponseTimeVsRequest"){
        choiceContainer = $("#choicesResponseTimeVsRequest");
        refreshResponseTimeVsRequest();
    } else if ( id == "choicesLatencyVsRequest"){
        choiceContainer = $("#choicesLatencyVsRequest");
        refreshLatenciesVsRequest();
    }
    var color = checked ? "black" : "#818181";
    if(choiceContainer != null) {
        choiceContainer.find("label").each(function(){
            this.style.color = color;
        });
    }
}

