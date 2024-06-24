import { UUID } from "crypto";
import { Agent } from "./Agent";

export interface Connection {
  id: UUID;
  agent: Agent;
}
