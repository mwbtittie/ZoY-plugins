package net.runelite.client.plugins.stunalch;

		import java.awt.*;
		import java.time.Duration;
        import java.time.Instant;
        import javax.inject.Inject;
        import javax.inject.Singleton;
        import lombok.extern.slf4j.Slf4j;
        import net.runelite.api.Client;
        import static net.runelite.api.MenuOpcode.RUNELITE_OVERLAY_CONFIG;
        import static net.runelite.client.ui.overlay.OverlayManager.OPTION_CONFIGURE;
		import static org.apache.commons.lang3.time.DurationFormatUtils.formatDuration;

		import net.runelite.api.Perspective;
		import net.runelite.client.ui.overlay.OverlayLayer;
		import net.runelite.client.ui.overlay.OverlayMenuEntry;
        import net.runelite.client.ui.overlay.OverlayPanel;
        import net.runelite.client.ui.overlay.OverlayPosition;
		import net.runelite.client.ui.overlay.OverlayUtil;
		import net.runelite.client.ui.overlay.components.TitleComponent;
        import net.runelite.client.ui.overlay.components.table.TableAlignment;
        import net.runelite.client.ui.overlay.components.table.TableComponent;
        import net.runelite.client.util.ColorUtil;

@Slf4j
@Singleton
class stunalchOverlay extends OverlayPanel
{

    private final Client client;
    private final stunalchPlugin plugin;
    private final stunalchConfig config;

    String timeFormat;
    private String infoStatus = "Starting...";

    @Inject
    private stunalchOverlay(final Client client, final stunalchPlugin plugin, final stunalchConfig config)
    {
        super(plugin);
        setPosition(OverlayPosition.BOTTOM_LEFT);
		//this.setPosition(OverlayPosition.DYNAMIC);
        this.client = client;
        this.plugin = plugin;
        this.config = config;
        getMenuEntries().add(new OverlayMenuEntry(RUNELITE_OVERLAY_CONFIG, OPTION_CONFIGURE, "Stun Alcher overlay"));
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
    	Shape clickbox;
        TableComponent tableComponent = new TableComponent();
        tableComponent.setColumnAlignments(TableAlignment.LEFT, TableAlignment.RIGHT);

        Duration duration = Duration.between(plugin.botTimer, Instant.now());
        timeFormat = (duration.toHours() < 1) ? "mm:ss" : "HH:mm:ss";
        tableComponent.addRow("Runtime:", formatDuration(duration.toMillis(),timeFormat));
        if (plugin.state != null)
        {
            if (!plugin.state.name().equals("TIMEOUT"))
                infoStatus = plugin.state.name();
        }
		/*if (this.plugin.splashNPC1 != null) {
			clickbox = Perspective.getClickbox(this.client, this.plugin.splashNPC1.getModel(), this.plugin.splashNPC1.getOrientation(), this.plugin.splashNPC1.getLocalLocation());
			if (clickbox != null) {
				OverlayUtil.renderClickBox(graphics, this.mouse(), clickbox, Color.CYAN);
			} else {
				OverlayUtil.renderNpcOverlay(graphics, this.plugin.splashNPC1, Color.CYAN, 1, 100, 80, this.client);
			}
		}*/

        tableComponent.addRow("State:", infoStatus);

        TableComponent tableDelayComponent = new TableComponent();
        tableDelayComponent.setColumnAlignments(TableAlignment.LEFT, TableAlignment.RIGHT);
        tableDelayComponent.addRow("Tick delay:", String.valueOf(plugin.timeout));

        if (!tableComponent.isEmpty())
        {
           // panelComponent.setBackgroundColor(ColorUtil.fromHex("#66000000")); //Material Dark default
            panelComponent.setPreferredSize(new Dimension(200,200));
            panelComponent.setBorder(new Rectangle(5,5,5,5));
            panelComponent.getChildren().add(TitleComponent.builder()
                    .text("Stun Alcher")
                    .color(Color.green)
                    .build());
            panelComponent.getChildren().add(tableComponent);
            panelComponent.getChildren().add(TitleComponent.builder()
                    .text("Delays")
                    .color(ColorUtil.fromHex("#F8BBD0"))
                    .build());
            panelComponent.getChildren().add(tableDelayComponent);
        }
        return super.render(graphics);
    }
	public net.runelite.api.Point mouse()
	{
		return client.getMouseCanvasPosition();
	}
}