interface IState {
    List<Agent> availableTowers;
    List<Agent> currentAgents;
    List<Agent> currentObjectives;
    List<Agent> currentAttributes;
    Image Background

    /**
     * Adds a copy of a specific type of agent to the state.
     */
    void placeAgent(Agent agent);
}