# Random Join Locations

A plugin for randomising the location at which a player first 
spawns into the Minecraft server.

The randomised spawn location is calculated using the world spawn
location as the starting point, then randomising the X and Z coordinates.

The maximum distance from spawn can be set in the configuration
(see configuration section).

## Spawning over Lava

If a player spawns over lava, the plugin will attempt to move the
new location away from the lava. In the event that the maximum search
distance (set in configuration) is reached and the player is still
over lava, the block below the player's feet will be changed to 
cobblestone.

## Spawning over Water

If a player spawns over water and is allowed by the configuration, 
the plugin will attempt to spawn a boat of the type specified in
the configuration (or OAK if unspecified) and place the player in 
it. If it is unable to do so, the player will be given a boat 
immediately in their main hand so they are able to save themselves 
a potentially long swim.

## Configuration Settings

Configuration settings are set in the plugin.yml file within the 
plugins/RandomJoinLocation folder.

### Show Debug Info In Console
`ShowDebug: true|false`

If the ShowDebug setting is set to true, debug information will
be output to the server console.

### Maximum Spawn Radius
`MaximumSpawnRadius: 10000`

If the MaximumSpawnRadius setting is set to a positive whole number, 
this will be the maximum range on the X and Z axes. The only potential 
exception that may result in this being exceeded is the if the new
spawn location calculated for the player is on lava.

### Spawn In Boat On Water
`SpawnInBoatOnWater: true`

If the new calculated spawn location for the player is over water and
this configuration setting is set to true, the plugin will attempt 
to spawn the player in a boat. The boat type will be defined in the
SpawnInBoatType configuration setting, or defaulted to OAK if the 
setting is absent or invalid.

### Spawn In Boat Type
`SpawnInBoatType: OAK_BOAT`

If the plugin is configured to attempt placing the player in a boat 
when spawning over water, this setting will define the boat type.

Valid values (up to Minecraft 1.19.2+) include:

Pre-1.19:
- ACACIA
- BIRCH
- DARK_OAK
- JUNGLE 
- OAK
- SPRUCE

1.19+:
- MANGROVE

1.19.2+
- BAMBOO (1.19.2+)

### Maximum Search Distance When Spawning Over Lava
`MaxSearchXOnLava: 10`

If a calculated spawn location is over lava, this configuration
setting defines the maximum distance that the plugin should 
search on the X-axis for a safer teleport location.

If the plugin is unable to find a non-lava block within the 
distance specified, the block under the player's feet will be
set to Cobblestone and the block they are standing in will be
set to water so that they have a chance to reach safety.
