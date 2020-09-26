package net.runelite.client.plugins.stunalch;

import static net.runelite.api.MenuOpcode.RUNELITE_OVERLAY_CONFIG;
import static net.runelite.client.ui.overlay.OverlayManager.OPTION_CONFIGURE;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.client.ui.overlay.OverlayMenuEntry;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayUtil;
import net.runelite.client.ui.overlay.components.table.TableAlignment;
import net.runelite.client.ui.overlay.components.table.TableComponent;

@Slf4j
@Singleton
class stunalchnpcOverlay extends OverlayPanel
{

    private final Client client;
    private final stunalchPlugin plugin;
    private final stunalchConfig config;


    @Inject
    private stunalchnpcOverlay(final Client client, final stunalchPlugin plugin, final stunalchConfig config)
    {
        super(plugin);
        //setPosition(OverlayPosition.BOTTOM_LEFT);
		this.setPosition(OverlayPosition.DYNAMIC);
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
		if (this.plugin.splashNPC1 != null) {
			clickbox = Perspective.getClickbox(this.client, this.plugin.splashNPC1.getModel(), this.plugin.splashNPC1.getOrientation(), this.plugin.splashNPC1.getLocalLocation());
			if (clickbox != null) {
				OverlayUtil.renderClickBox(graphics, this.mouse(), clickbox, Color.CYAN);
			} else {
				OverlayUtil.renderNpcOverlay(graphics, this.plugin.splashNPC1, Color.CYAN, 1, 100, 80, this.client);
			}
		}
        return super.render(graphics);
    }
	public net.runelite.api.Point mouse()
	{
		return client.getMouseCanvasPosition();
	}
}