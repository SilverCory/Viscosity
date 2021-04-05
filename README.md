# Viscosity

### WTF is this?
Solving the issue of proxy <---> server communication.
Intended to be a drop in, no configuration replacement for socket communication. 

#### The current solutions:

##### Sockets
- Requires a new port to be opened
- Can be messy and difficult to maintain/setup.
- (GOOD) Allows for more refined firewalling/access control.

##### PluginMessages
- (KEY ISSUE) Requires a player connection to communicate
- (GOOD) it's an already established protocol inside Minecraft itself.

##### Some sort of message queue/database
- Requires additional services to carry out what should be a simple task.
- Potential abuse of services to perform tasks that they're not intended for.

#### Viscosity's solution:
- Requires little to no setup.
- No additional services/ports required.
- Doesn't need a player connection.

How?

Viscosity uses websockets on the already open and configured port of the minecraft server.

This allows for a minimal configuration, and the avoidance of several issues outlined above.

### Setup
<!-- TODO --><small>(Yet to be implemented)</small>
1. Install all bukkit plugins (Viscosity and ProtocolLib).
2. Install all bungee plugins (Viscosity).
3. Join the proxy and server you wish to configure.
    - Multiple proxies can be configured by carrying out these steps on each proxy.
4. Type `/viscosity configure`
    - This will automatically exchange the security tokens required for the proxy and server to communicate.
    - You'll need the permission (on bukkit) `viscosity.configure`
5. To configure all servers in your network you can type `/viscosity configure all`.
    - Ensure you have the `viscosity.configure` permission on all servers.
6. Voila! Any plugins that rely on Viscosity

### TODO
- Allow proxy <---> proxy communication
    - This will be move involved since these details aren't already used.
- WebSocket binary data.
    - Currently all data is turned into JSON.