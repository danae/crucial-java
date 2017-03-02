package com.dengsn.crucialalpha.player;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class PlayerManager
{
  // Variables
  private final List<Player> players = new ArrayList<>();
  
  // Resource management
  public void registerPlayer(Player player)
  {
    this.players.add(player);
  }
  public void registerPlayers(Collection<? extends Player> players)
  {
    this.players.addAll(players);
  }
  public void unregisterPlayer(Player player)
  {
    this.players.remove(player);
  }
  public void unregisterPlayers(Collection<? extends Player> players)
  {
    this.players.removeAll(players);
  }
  public Player getPlayer(String name)
  {
    return this.players.stream()
      .filter(p -> p.getName().equals(name))
      .findFirst()
      .orElse(null);
  }
  public <P extends Player> P getPlayer(String name, Class<P> type)
  {
    return (P)this.getPlayer(name);
  }
  public Player getFirst()
  {
    return this.players.isEmpty() ? null : this.players.get(0);
  }
  public <P extends Player> P getFirst(Class<P> type)
  {
    return (P)this.getFirst();
  }
  public List<Player> getPlayers()
  {
    return this.players;
  }
  public <P extends Player> List<P> getPlayers(Class<P> type)
  {
    return this.players.stream()
      .map(p -> (P)p)
      .collect(Collectors.toList());
  }
}
