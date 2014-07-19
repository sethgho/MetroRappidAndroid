# MetroRappid for Android

![MetroRappid for Android logo](https://cloud.githubusercontent.com/assets/1275831/3210441/0128e4a2-eec1-11e3-8622-fc947f7c305c.png)

~~On the Google Play store by the end of the week.~~ Abandoned in favor of [MetroRappid.com](http://metrorappid.com). Source at [github.com/luqmaan/MetroRappid](github.com/luqmaan/MetroRappid)

MetroRappid for Android (winner of ATX Hack For Change) launched, but had problems working with the Google Maps API. :crying_cat_face: We ended up taking it down from the Play Market and began working on a more open solution, and so was born [github.com/luqmaan/MetroRappid](github.com/luqmaan/MetroRappid).


## Problem

- People don't ride the bus because it can seem unreliable: buses arrive anytime from 10 minutes before their scheduled time to 20 minutes after
- The solution to that is real-time data
- But if the real-time data is hard to access, people won't use it
- The MetroRapid (801) and MetroRail (550) routes have real-time data
- Only CapMetro's official app have access to that real-time data
- But, CapMetro's app is hard to use. See [CapMetroApp: When should I take the bus home?](https://github.com/sethgho/MetroRappidAndroid/wiki/CapMetro-App---When-should-I-take-the-bus-home)


##Solution

We reverse engineered CapMetro's app to find out how they were getting real-time data. Then we [documented the hidden/secret CapMetro API](https://github.com/luqmaan/MetroRappid/wiki/The-CapMetro-API).
We then used that API and built an Android app that shows real-time arrivals for MetroRapid and MetroRail, really quickly.

Step 1. Select A Route And Direction

![Select A Route And Direction](https://cloud.githubusercontent.com/assets/1275831/3142686/33d32040-e9ce-11e3-8252-2f58ac6ecef4.jpg)


Step 2. 
MetroRappid automatically finds the nearest stop and vehicle

![View the closest stop and vehicle](https://cloud.githubusercontent.com/assets/1275831/3142698/407f2950-e9cf-11e3-9d07-fe34c3519909.jpg)

Step 3. Get on the bus

## Team

- https://github.com/sethgho
- https://github.com/luqmaan
- https://github.com/scasketta
- https://github.com/johntyree
- https://github.com/caskman


## Get MetroRappid for Android

Download it on the Google Play store at (soon).
