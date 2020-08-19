$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("C:/Users/VENKATRAMAN/workspace/CricbuzzCucumber/src/main/java/Features/liveMatchScoreCard.feature");
formatter.feature({
  "line": 1,
  "name": "getting deatils from crirbuzz site",
  "description": "",
  "id": "getting-deatils-from-crirbuzz-site",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "clicking live score page butn",
  "description": "",
  "id": "getting-deatils-from-crirbuzz-site;clicking-live-score-page-butn",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "user on home page",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "click livescore and score card butn",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "getting score card deatils and writing in workbook",
  "keyword": "Then "
});
formatter.match({
  "location": "LiveScoreCardDeatils.user_on_home_page()"
});
formatter.result({
  "duration": 13707244118,
  "status": "passed"
});
formatter.match({
  "location": "LiveScoreCardDeatils.click_livescore_and_score_card_butn()"
});
formatter.result({
  "duration": 4690968825,
  "status": "passed"
});
formatter.match({
  "location": "LiveScoreCardDeatils.getting_score_card_deatils_and_writing_in_workbook()"
});
formatter.result({
  "duration": 5126425799,
  "status": "passed"
});
});