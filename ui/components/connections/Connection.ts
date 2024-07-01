import { UUID } from "crypto";
import { Agent } from "./Agent";

export interface Connection {
  id: UUID;
  pid: number;
  serviceName: string;
  otelVersion: string;
  gepardVersion: string;
  startTime: string;
  javaVersion: string;
}
