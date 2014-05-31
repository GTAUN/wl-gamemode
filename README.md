PROJECT NEW WL-World (Based on Shoebill)
=================
Based on the idea of WL-World, we reproduce New WL-World by using Java and the Shoebill API. 
There is no connection between the New WL-World and WL-World, but we still want to say "Thank you" for the people who make a contribution on the WL-World.
[Click here](http://www.gtabbs.com/read-gta-tid-2593634.html) may take you to the WL-World official site.

DESIGN CONCEPT
-----------------
* Provide statistics and report function as far as possible.
* Provide the re-edit function for all user data which generated in game as much as we can.
* Ensure that the functions in the project are dynamic-adaptatable.
* Make sure the code is easy to read.
* High quality of the code.

FEATURE 
-----------------
* Focus on the user friendly design.
* Targeting on powerful and good visual qualities.
* Multi-language Support.
* Using dialogs as the main user interface, using the command line as secondary function.
* Flexible dialog design, you may add sections(in dialogs) when needed.
* Using hot-keys in order to simplify basic instructions.
* Supports the translation between Simplified Chinese and Traditional Chinese （Based on Shoebill).
* The project may contain many components, you may decide whether part of the components you want to enabled or disable at any time.
* Providing a interactive interface for all components, which allows coordination with other components.
* Using YAML as the format of the configuration files.
* Using MongoDB Database (and Morphia ORM) as data storage.

AVALIABLE COMPONENTS
-----------------

These components are currently planned:

(Finished)
* `wl-language` Multi-language support (1.0)
* `wl-vehicle-manager` Vehicle manager (1.0)

(In progress)
* `wl-common` The libraries tool-kit of the project
* `wl-gamemode` main component, in charge of coordinating the work of components
* `wl-race` Advance racing system
* `wl-teleport` Teleport system
* `wl-chat-channel` Chatting system

(Planned)
* `wl-auth` Account authentication and permission system
* `wl-admin` Administrator system
* `wl-simple-dm` Simple deathmatch system
* `wl-mail` Mail system

(Pending)
* `wl-radio` Online radio support
* `wl-anticheat` Simple anticheat system

DEPENDENT COMPONMENTS/OPEN SOURCE PROJECTS
-----------------

* `shoebill-common` Shoebill generic libraries tool-kit
* `common-lang3` Apache Java generic libraries tool-kit
* `mongo-java-driver` Mongo DB Java database driver
* `morphia` ORM

RECOMMAND COMPONENT
-----------------
* `mk-plugin-manager` MK 's Shoebill plugin- manager.

OPEN SOURCE LICENSE
-----------------
[GNU AFFERO GENERAL PUBLIC LICENSE, version 3](http://www.gnu.org/licenses/agpl-3.0.html)

LIMITS AND WARNING
-----------------

Please observe the following terms, otherwise please do not use any component or any code from this project:
* Please follow the clause in AGPL v3 license, that is, to maintain its source be opened after any modification to the code.
* DO NOT remove any information about copyright and license.
* DO NOT remove any information and message about original authors and copyright.
* DO NOT use any component or code of this project on any commercial/profit-making server.
* DO NOT use "WL-World", "New WL-World" or any relevant name as server name on the server WITHOUT authorization.
* DO NOT advertise the server by using "WL-World", "New WL-World" or any relevant name WITHOUT authorization.
