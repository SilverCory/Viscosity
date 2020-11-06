/**
 * Classes to provide a frame processing function using reflection, annotations
 * and interfaces to provide registrable, dynamic and specific objects to a targeted method.
 * <p>
 * Based of code from the <a href="https://github.com/SpigotMC/BungeeCord/tree/master/event/src/main/java/net/md_5/bungee/event">Bungeecord event module</a>.
 * <p>
 * The implementing class along with the frame data.:
 * <pre>
 *     @lombok.Data
 *     @AllArgsConstructor
 *     static class MyData {
 *         String name;
 *         String colour;
 *         int length;
 *     }
 *
 *     static class MyListener implements FrameListener {
 *
 *         @FrameHandler(frameIdentifier = "FrameIdentifierFoo")
 *         public void handleFoo(MyData obj) {
 *             System.out.println("Foo: " +obj.toString());
 *         }
 *
 *         @FrameHandler(frameIdentifier = "FrameIdentifierBar")
 *         public void handleBar(Object obj) {
 *             System.out.println("Bar: " + obj.toString());
 *         }
 *     }
 * </pre>
 * <p>
 * This should be implemented in the plugin/caller by using:
 * <pre>
 *     public void onEnable() {
 *         Viscosity.getAPI().getFrameBus().register(this, new MyListener());
 *     }
 *
 *     public void onDisable() {
 *         Viscosity.getAPI().getFrameBus().unregister(this);
 *     }
 * </pre>
 */
package co.ryred.dev.viscosity.api.frame;

